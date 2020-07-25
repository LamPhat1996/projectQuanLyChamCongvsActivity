package tdc.edu.vn.projectquanlychamcongvsactivity.model;

public class CardViewModel {
    //pizza 1 vaf 2 sinh ra tu` day
    int imageResourceId;

    public CardViewModel( int imageResourceId) {

        this.imageResourceId = imageResourceId;
    }

    public CardViewModel() {
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
