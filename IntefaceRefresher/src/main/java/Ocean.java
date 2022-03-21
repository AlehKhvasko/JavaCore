public class Ocean implements FishingTips{
    public Ocean() {
        System.out.println("Boat?");
    }

    @Override
    public void showTips() {
        System.out.println("You need a boat.");
    }
}
