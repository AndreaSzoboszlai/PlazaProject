package com.codecool.plaza.api;

import java.util.*;

public class ShopImpl implements Shop{

    private String name;
    private String owner;
    private Map<Long, ShopImpl.ShopEntryImpl> products;
    private boolean isOpen;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.products = new HashMap<Long, ShopImpl.ShopEntryImpl>();
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public List<Product> getProducts() throws ShopIsClosedException {
        if (isOpen == true) {
            List<Product> produCTS = new ArrayList<Product>();
            for (ShopImpl.ShopEntryImpl element : products.values()) {
                produCTS.add(element.getProduct());
            }
            return produCTS;
        } else {
            throw new ShopIsClosedException();
        }
    }

    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen == true) {
            for (ShopImpl.ShopEntryImpl element : products.values()) {
                if (name.equals(element.getProduct().getName())) {
                    return element.getProduct();
                }
            }
            throw new NoSuchProductException();
        } else {
            throw new ShopIsClosedException();
        }
    }

    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen == true) {
            ShopImpl.ShopEntryImpl shopImpl = products.get(barcode);
            if (shopImpl == null) {
                throw  new NoSuchProductException();
            }
            return shopImpl.getPrice();
        } else {
            throw new ShopIsClosedException();
        }
    }

    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        if (isOpen == true) {
            ShopImpl.ShopEntryImpl shopImpl = products.get(barcode);
            if (shopImpl != null) {
                return true;
            }
            return false;
        } else {
            throw new ShopIsClosedException();
        }
    }

    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        if (isOpen == true) {
            if (products.containsKey(product.getBarCode())) {
                throw new ProductAlreadyExistsException();
            } else {
                products.put(product.getBarCode(), new ShopEntryImpl(product, quantity, price));
            }
        } else {
            throw new ShopIsClosedException();
        }
    }

    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen == true) {
            if (hasProduct(barcode) == false) {
                throw new NoSuchProductException();
            }
            for (ShopImpl.ShopEntryImpl element : products.values()) {
                if (barcode == (element.getProduct().getBarCode())) {
                    element.increaseQuantity(quantity);
                }
            }
        } else {
            throw new ShopIsClosedException();
        }
    }

    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        if (isOpen == true) {
            if (products.containsKey(barcode)) {
                for (ShopImpl.ShopEntryImpl element : products.values()) {
                    if (barcode == (element.getProduct().getBarCode())) {
                        if (element.getQuantity() == 0) {
                            throw new OutOfStockException();
                        } else {
                            element.decreaseQuantity(1);
                            return element.getProduct();
                        }
                    }
                }
            }
            throw new NoSuchProductException();
        } else {
            throw new ShopIsClosedException();
        }
    }

    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        if (isOpen == true) {
            List<Product> boughtProducts = new ArrayList<Product>();
            if (products.containsKey(barcode)) {
                for (ShopImpl.ShopEntryImpl element : products.values()) {
                    if (barcode == (element.getProduct().getBarCode())) {
                        if (element.getQuantity() == 0) {
                            throw new OutOfStockException();
                        } else {
                            element.decreaseQuantity(quantity);
                            boughtProducts.add(element.getProduct());
                        }
                    }
                }
                return boughtProducts;
            } else {
                throw new NoSuchProductException();
            }
        } else {
            throw  new ShopIsClosedException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopImpl)) return false;
        ShopImpl shop = (ShopImpl) o;
        return Objects.equals(name, shop.name) &&
                Objects.equals(owner, shop.owner) &&
                Objects.equals(products, shop.products);
    }


    private class ShopEntryImpl{
        private Product product;
        private int quantity;
        private float price;

        private ShopEntryImpl(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void increaseQuantity(int amount) {
            quantity += amount;
        }

        public void decreaseQuantity(int amount) {
            quantity -= amount;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }


    }
    public String toString() {
        return "Shop's name: " + name + " Owner: " + owner;
    }
}
