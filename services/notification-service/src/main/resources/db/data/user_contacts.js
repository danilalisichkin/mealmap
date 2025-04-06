const MONGO_HOST = "localhost:27026";
const DATABASE = "notification_database";
const COLLECTION_NAME = "user_contacts";

const user_contacts = [
  {
    _id: ObjectId('111111111111111111111111'),
    userId: '0e3901b5-fb3f-44e4-b691-adac0a735789',
    email: "client-employee@company.com",
    phoneNumber: "375291234567",
    tgChatId: null,
    _class: 'com.mealmap.notificationservice.doc.UserContacts'
  }
];

const db = connect(`${MONGO_HOST}/${DATABASE}`);

db[COLLECTION_NAME].insertMany(user_contacts);
