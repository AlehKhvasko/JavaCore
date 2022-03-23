public class Display {
    private FishingTips tips;

    public void setTips(FishingTips tips){
         this.tips = tips;
    }

    public void displayTips() {
        tips.showTips();
    }
}
