public class Location {
    int x;
    int y;

    /**
     * Here's the constructor of the class
     * @param x
     * @param y
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Location location) {
        return (location.x == this.x && location.y == this.y);
    }

    /**
     * This method is for changing the x-coordinate
     * @param x
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * This method is to get the x-coordinate
     * @return
     */
    public int getX(){
        return x;
    }

    /**
     * This method is for changing the y-coordinate
     * @param y
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * This method is to get the x-coordinate
     * @return
     */
    public int getY(){
        return y;
    }
}



