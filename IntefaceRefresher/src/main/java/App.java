public class App {
    public static void main(String[] args) {

        //Regular implementation
        Display ocean = new Display();
        ocean.setTips(new Ocean());
        ocean.displayTips();

        //Anonymous classes
        Display coloradoRiver =  new Display();
        FishingTips newRef = new FishingTips() {
            @Override
            public void showTips() {
                System.out.println("No tips for today. Sorry...");
            }
        };
        coloradoRiver.setTips(newRef);

        coloradoRiver.displayTips();

        // Lambda expression
        Display lambdaTravis =  new Display();
        lambdaTravis.displayTips();


        /**
         * Igor's example;
         */

//       FishingTips tipsLambda = (int x, int y) -> {
//          return x + y;
//       };
//        Calculator sum = (x, y) -> x + y;
            Operation op = new Operation() {
                @Override
                public int run(int x, int y) {
                    return 0;
                }
            };

            Operation op1 = (int x, int y) -> {
                int temp = x + y;
                return x + y;
            };
            op1.run(3,3);

            Operation sum = (x, y) -> x + y;
            System.out.println(sum.run(1, 2));
            Operation minus = (x, y) -> x - y;
            System.out.println(minus.run(2, 2));
            Operation random = (x, y) -> {
                for (int i = 0; i < y; i++) {
                    x *= x;
                }
                return x;
            };
            System.out.println(random.run(2, 4));
    }
    //Functional interface
    interface Operation {
        int run(int x, int y);
    }


}
