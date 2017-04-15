package ru.platonov.edu;

/**
 * Created by platonov on 31/03/17.
 */
public class InnerClasses {

    private InnerClasses(){

    }

    public void bar() {

        class InnerLocalClass{

        }

    }

    static final protected class StaticNestedClass{

        public void foo(){
            InnerClasses innerClasses = new InnerClasses();
        }

        static final protected class StaticNestedNestedClass {

        }

    }

}
