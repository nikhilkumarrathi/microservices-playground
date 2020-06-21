export interface Product{
  name: string;
  url : string;
  id: number;
}

const products: Product[] = [
  {
    id: 0,
    name: "Product 1",
    url: "images/products/product1.jpg",
  },
  {
    id: 1,
    name: "Product 2",
    url: "images/products/product2.jpg",
  },
  {
    id: 2,
    name: "Product 3",
    url: "images/products/product3.jpg",
  },
  {
    id: 3,
    name: "Product 4",
    url: "images/products/product4.jpg",
  },
];

export default products;
