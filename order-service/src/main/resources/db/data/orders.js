const MONGO_HOST = "localhost:27020";
const DATABASE = "order_database";
const COLLECTION_NAME = "orders";

const orders = [
  {
    _id: ObjectId('111111111111111111111111'),
    userId: '0e3901b5-fb3f-44e4-b691-adac0a735789',
    deliveryAddress: {
      fullAddress: 'Советская ул., дом 6',
      coordinates: [-118.358467, 34.063621]
    },
    status: 'COMPLETED',
    paymentInfo: {
      totalAmount: 200,
      discountAmount: 100,
      paymentId: 1,
      paymentStatus: 'PAID'
    },
    orderedAt: new Date('2024-01-26T14:30:45Z'),
    readyAt: new Date('2024-01-26T14:40:45Z'),
    completedAt: new Date('2024-01-26T14:50:45Z'),
    items: [
      { productId: 1, priceWhenOrdered: 50, quantity: 4 },
      { productId: 2, priceWhenOrdered: 100, quantity: 1 }
    ],
    _class: 'com.mealmap.orderservice.doc.Order'
  },
  {
    _id: ObjectId('222222222222222222222222'),
    userId: 'ef38cc33-c3df-4b76-aa8c-de6be555f45c',
    deliveryAddress: {
      fullAddress: 'Советский вокзал',
      coordinates: [-118.358467, 34.063621]
    },
    status: 'READY',
    paymentInfo: {
      totalAmount: 400,
      discountAmount: 0,
      paymentId: 2,
      paymentStatus: 'PENDING'
    },
    orderedAt: new Date('2024-02-26T14:30:45Z'),
    readyAt: null,
    completedAt: null,
    items: [
      { productId: 6, priceWhenOrdered: 100, quantity: 2 },
      { productId: 8, priceWhenOrdered: 200, quantity: 1 }
    ],
    _class: 'com.mealmap.orderservice.doc.Order'
  }
];

const db = connect(`${MONGO_HOST}/${DATABASE}`);

db[COLLECTION_NAME].insertMany(orders);
