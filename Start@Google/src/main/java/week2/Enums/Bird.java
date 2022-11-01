package week2.Enums;

public enum Bird {
    CROW("Corvus brachyrhynchos"){
        @Override
        public void sing(){
            System.out.println("Caw-caw-caw-caw-koodle-yah");
        }
    },
    CUCKOO("Cuculus canorus"){
        @Override
        public void sing(){
            System.out.println("Ku-ku");
        }
    },
    LOON("Gavia immer"){
        @Override
        public void sing(){
            System.out.println("Cooo-leee");
        }
    },
    PEAFOWL("Pavo cristatus"){
        @Override
        public void sing(){
            System.out.println("Pia-ow ey-awe");
        }
    };

    private final String name;
    public abstract void sing();

    Bird(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }
}
