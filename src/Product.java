import javafx.scene.image.Image;

public class Product implements Comparable<Product> {
    private String title, console, description;
    private double price;
    private int rating, units;
    private Image image;

    public Product(String title, String console, String description, double price, int rating, int units, Image image) {
        setTitle(title);
        setConsole(console);
        setDescription(description);
        setPrice(price);
        setRating(rating);
        setUnits(units);
        setImage(image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("You Did Not Enter A Title.");
        } else {
            this.title = title;
        }
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        //String[] vaildConsoles = ("Xbox", "PlayStation", "PC", "GameCube")
        // for (vaildConsoles each vaildConsoles)

        if (console.isEmpty()) {
            throw new IllegalArgumentException("You Did Not Enter A Console.");
        } else {
            this.console = console;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 1000 && price < 0) {
            throw new IllegalArgumentException("Price Must Be Between 1000 - 0");
        } else {
            this.price = price;
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating > 10 && rating < 0) {
            throw new IllegalArgumentException("Rating Must Be Between 10 - 0");
        } else {
            this.rating = rating;
        }
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        if (units < 0) {
            throw new IllegalArgumentException("Units Cannot Be Lower Than 1");
        } else {
            this.units = units;
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("You Did Not Enter A Description.");
        } else {
            this.description = description;
        }
    }

    @Override
    public int compareTo(Product product) {
        if (this.getTitle().compareToIgnoreCase(product.getTitle()) == 0)
            return this.getConsole().compareToIgnoreCase(product.getConsole());
        else
            return getTitle().compareToIgnoreCase(product.getTitle());
    }

    @Override
    public String toString() {
        return String.format("%s %s Units @ $%.2f each\n"
                ,
                this.title, this.units, this.price);
    }
}