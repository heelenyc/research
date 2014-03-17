package com.research.common.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.impl.JsonWriteContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;
import org.codehaus.jackson.util.CharTypes;

/**
 * Json util class.
 * 
 */
public final class JsonUtil {
    private static ObjectMapper mapper = null;

    /**
     * Constructor
     */
    private JsonUtil() {
    }

    private static ObjectMapper getObjectMapper() {
        if (mapper == null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
            mapper = new ObjectMapper();
            mapper.setDateFormat(df);
            mapper.setSerializationInclusion(Inclusion.NON_NULL);

            CustomSerializerFactory serializerFactory = new CustomSerializerFactory();
            serializerFactory.addSpecificMapping(String.class, new StringUnicodeSerializer());
            mapper.setSerializerFactory(serializerFactory);
        }
        return mapper;
    }

    /**
     * From json string to bean.
     * 
     * @param <T> bean class type.
     * @param json json string.
     * @param clazz bean class.
     * @return the bean instance.
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        if (json != null && json.length() > 0) {
            try {
                return getObjectMapper().readValue(json, clazz);
            } catch (Exception e) {
                throw new RuntimeException("JSONString : " + json, e);
            }
        }
        return null;
    }

    /**
     * To json string.
     * 
     * @param object the object.
     * @return json string.
     */
    public static String toString(Object object) {
        return toString(object, getObjectMapper());
    }

    /**
     * To json string base on given mapper.
     * 
     * @param object object
     * @param mapper mapper
     * @return json string.
     */
    public static String toString(Object object, ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class StringUnicodeSerializer extends NonTypedScalarSerializerBase<String> {
        private final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
                'f' };
        private final int[] ESCAPE_CODES = CharTypes.get7BitOutputEscapes();

        public StringUnicodeSerializer() {
            super(String.class);
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException,
                JsonGenerationException {
            int status = ((JsonWriteContext) gen.getOutputContext()).writeValue();
            switch (status) {
            case JsonWriteContext.STATUS_OK_AFTER_COLON:
                gen.writeRaw(':');
                break;
            case JsonWriteContext.STATUS_OK_AFTER_COMMA:
                gen.writeRaw(',');
                break;
            case JsonWriteContext.STATUS_EXPECT_NAME:
                throw new JsonGenerationException("Can not write string value here");
            }
            gen.writeRaw('"');// 写入JSON中字符串的开头引号
            for (char c : value.toCharArray()) {
                if (c >= 128) {
                    writeUnicodeEscape(gen, c); // 为所有非ASCII字符生成转义的unicode字符
                } else {
                    // 为ASCII字符中前128个字符使用转义的unicode字符
                    int code = (c < ESCAPE_CODES.length ? ESCAPE_CODES[c] : 0);
                    if (code == 0) {
                        gen.writeRaw(c); // 此处不用转义
                    } else if (code < 0) {
                        writeUnicodeEscape(gen, (char) (-code - 1)); // 通用转义字符
                    } else {
                        writeShortEscape(gen, (char) code); // 短转义字符 (\n \t ...)
                    }
                }
            }
            gen.writeRaw('"');// 写入JSON中字符串的结束引号
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            return createSchemaNode("string", true);
        }

        private void writeUnicodeEscape(JsonGenerator gen, char c) throws IOException {
            if (Character.isSupplementaryCodePoint(c)) {
                // Handle supplementary unicode values which are not representable in
                // javascript. We deal with these by escaping them as two 4B sequences
                // so that they will round-trip properly when sent from java to javascript
                // and back.
                char[] surrogates = Character.toChars(c);
                writeUnicodeEscape(gen, surrogates[0]);
                writeUnicodeEscape(gen, surrogates[1]);
                return;
            }

            gen.writeRaw('\\');
            gen.writeRaw('u');
            gen.writeRaw(HEX_CHARS[(c >>> 12) & 0xF]);
            gen.writeRaw(HEX_CHARS[(c >>> 8) & 0xF]);
            gen.writeRaw(HEX_CHARS[(c >>> 4) & 0xF]);
            gen.writeRaw(HEX_CHARS[c & 0xF]);
        }

        private void writeShortEscape(JsonGenerator gen, char c) throws IOException {
            gen.writeRaw('\\');
            gen.writeRaw(c);
        }
    }

}
