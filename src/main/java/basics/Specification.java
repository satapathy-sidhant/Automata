package basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
public interface Specification<T> {
    boolean checkSpecification(T t);
}

@FunctionalInterface
interface Filters<T> {
    Stream<T> setFilters(List<T> t);
}

class ColorFilter implements Filters<Product> {

    Colors color;

    public ColorFilter(Colors color) {
        this.color = color;
    }

    public Stream<Product> setFilters(List<Product> p) {
        return p.stream().filter(c -> c.getColor().equals(color));
    }

}

class SizeFilter implements Filters<Product> {

    Sizes size;

    public SizeFilter(Sizes size) {
        this.size = size;
    }

    public Stream<Product> setFilters(List<Product> p) {
        return p.stream().filter(c -> c.getSizes().equals(size));
    }

}

class MultiFilter implements Filters<Product>{

    Colors color;
    Sizes size;

    MultiFilter(Colors color , Sizes size){
        this.color = color;
        this.size = size;
    }

    public Stream<Product> setFilters(List<Product> p){
        return p.stream().filter(c -> c.getColor().equals(color) && c.getSizes().equals(size));
    }

}

class ColorSpecification implements Specification<Product> {

    Colors color;

    public ColorSpecification(Colors color) {
        this.color = color;
    }

    public boolean checkSpecification(Product P) {
        return P.getColor().equals(color.colorName());
    }
}

class SizeSpecification implements Specification<Product> {

    Sizes size;

    public SizeSpecification(Sizes size) {
        this.size = size;
    }

    public boolean checkSpecification(Product P) {
        return P.getSizes().equals(size.sizeUnit());
    }
}


class Product {


    String name;
    Colors color;
    Sizes size;

    Product(String name , Colors color , Sizes  size){
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Sizes getSizes() {
        return size;
    }

    public void setSizes(Sizes sizes) {
        this.size = sizes;
    }

    @Override
    public String toString(){
        return name + " " + color + " " + size;
    }
}

enum Colors {

    GREEN("green"),
    BLUE("blue"),
    YELLOW("yellow"),
    RED("red"),
    HYBRID("mixed");

    private String color;

    Colors(String color) {
        this.color = color;
    }

    public String colorName() {
        return color;
    }
}

enum Sizes {

    SMALL("small"),
    LARGE("large"),
    MEDIUM("medium"),
    EXTRA_LARGE("very large");

    private String size;

    Sizes(String size) {
        this.size = size;
    }

    public String sizeUnit() {
        return size;
    }
}

class demo {

    public static void main(String[] args) {
        Product sidhant_house = new Product("Sidhant house" , Colors.HYBRID , Sizes.EXTRA_LARGE);
        Product pranati_house = new Product("Pranati house" , Colors.HYBRID , Sizes.EXTRA_LARGE);
        Product chinku_house = new Product("Chinku house" , Colors.BLUE , Sizes.LARGE);

        List<Product> products = Arrays.asList(sidhant_house, pranati_house, chinku_house);

        Filters<Product> hybridFilter = new ColorFilter(Colors.HYBRID);
        hybridFilter.setFilters(products).forEach(filtered -> {
            System.out.println(filtered.toString());
        });
    }
}
