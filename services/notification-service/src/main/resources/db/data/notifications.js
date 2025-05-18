const MONGO_HOST = "localhost:27026";
const DATABASE = "notification_database";
const COLLECTION_NAME = "notifications";

const notifications = [
  {
    _id: ObjectId('111111111111111111111111'),
    userId: '0e3901b5-fb3f-44e4-b691-adac0a735789',
    channel: 'EMAIL',
    status: 'SENT',
    subject: 'Заказ № 111111111111111111111111 готовится',
    message: 'Дорогой пользователь, Ваш заказ начали готовить!',
    sentAt: new Date('2025-04-01T14:40:45Z'),
    _class: 'com.mealmap.notificationservice.doc.Notification'
  },
  {
    _id: ObjectId('222222222222222222222222'),
    userId: '0e3901b5-fb3f-44e4-b691-adac0a735789',
    channel: 'TELEGRAM',
    status: 'SENT',
    subject: 'Заказ № 111111111111111111111111 собран',
    message: 'Дорогой пользователь, Ваш заказ собран и скоро будет передан в доставку!',
    sentAt: new Date('2025-04-01T14:50:45Z'),
    _class: 'com.mealmap.notificationservice.doc.Notification'
  }
];

const db = connect(`${MONGO_HOST}/${DATABASE}`);

db[COLLECTION_NAME].insertMany(notifications);
