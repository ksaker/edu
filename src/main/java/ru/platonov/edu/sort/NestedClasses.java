package ru.platonov.edu.sort;

/**
 * Created by platonov on 19/02/17.
 */
public class NestedClasses {

    public static final int public_static_final = 1;
    private static final int private_static_final = 2;
    public static int public_static_param = 3;
    private static int private_static_param = 4;

    public int public_param = 5;
    private int private_param = 6;

    private NestedClasses(){

    }

    public void publicMember(){

    }

    public static void publicStaticMember(){

    }

    static class StaticNestedClass{

        interface innerInterface{

        }

        public static class InnerClass {

        }

        static int inner_static_param = 7;

        public static void staticMember(){

        }

        public void member(){
            int a = public_static_final;
            int b = private_static_final;
            int c = public_static_param;
            int d = private_static_param;
            publicStaticMember();
            new NestedClasses();

        }

    }

     class InnerClass {

        int param = 10;

        class InnerInnerClass{

        }

        void member (){
            int a = public_static_final;
            int b = private_static_final;
            int c = public_static_param;
            int d = private_static_param;
            int e = public_param;
            int f = private_param;

            NestedClasses.this.publicMember();
            publicStaticMember();
            new NestedClasses();

        }

    }

    public void localClassMethod(int localParameter){

        int localVariable = 10;

        class LocalClass{

            public void foo(){
                int a = public_static_final;
                int b = private_static_final;
                int c = public_static_param;
                int d = private_static_param;
                int e = public_param;
                int f = private_param;

                int g = localVariable;
                int h = localParameter;

            }

        }


    }

    public static void main(String[] args) {
        NestedClasses nestedClasses = new NestedClasses();
        InnerClass innerClass = nestedClasses.new InnerClass();
    }


}
