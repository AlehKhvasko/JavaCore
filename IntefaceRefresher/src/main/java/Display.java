public class Display {
    private FishingTips tips;

    public Display(FishingTips tips){
        this.tips = tips;
    }

    public void displayTips(){
        tips.showTips();
    }
}
