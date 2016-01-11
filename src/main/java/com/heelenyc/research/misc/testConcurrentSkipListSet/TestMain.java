package com.heelenyc.research.misc.testConcurrentSkipListSet;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author yicheng
 * @since 2015年3月27日
 *
 */
public class TestMain {

    private static Set<Entity> set = new ConcurrentSkipListSet<Entity>();
    
    public static void main(String[] args) {
        set.add(new Entity("hello"));
        set.add(new Entity("hello"));
        
        for(Entity s:set){
            System.out.println(s.getField());
        }
    }
    
    private static class Entity {
        private String field;

        /**
         * 
         */
        public Entity(String f) {
            this.field = f;
        }
        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }
}
