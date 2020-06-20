export interface Product{
  name: string;
  url : string;
  id: number;
}

const products: Product[] = [
  {
    id: 0,
    name: "Product 1",
    url: "https://picsum.photos/200/250",
  },
  {
    id: 1,
    name: "Product 2",
    url: "https://picsum.photos/201/250",
  },
  {
    id: 2,
    name: "Product 3",
    url: "https://picsum.photos/200/251",
  },
  {
    id: 3,
    name: "Product 4",
    url: "https://picsum.photos/202/250",
  },
];

export default products;
