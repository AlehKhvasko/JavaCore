public class App {
    public static void main(String[] args) {

        //Regular implementation
        Display ocean = new Display(new Ocean());
        ocean.displayTips();

        //Anonymous classes
        Display coloradoRiver =  new Display(
                new FishingTips() {
                    @Override
                    public void showTips() {
                        System.out.println("No tips for today. Sorry...");
                    }
                });
        coloradoRiver.displayTips();

        // Lambda expression
        Display lambdaTravis =  new Display(
                ()->{new LakeTravis().showTips();}
        );
        lambdaTravis.displayTips();

        //method references
        new Display(Ocean::new).displayTips();
    }


}
