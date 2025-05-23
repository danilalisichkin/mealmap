const MONGO_HOST = "localhost:27022";
const DATABASE = "promo_database";
const COLLECTION_NAME = "promo_stats";

const promo_stats = [
  {
    _id: ObjectId('111111111111111111111111'),
    promoCode: '2025',
    userId: '0e3901b5-fb3f-44e4-b691-adac0a735789',
    createdAt: new Date('2025-01-04T10:00:00Z'),
    _class: 'com.mealmap.promoservice.document.PromoStat'
  }
];

const db = connect(`${MONGO_HOST}/${DATABASE}`);

db[COLLECTION_NAME].insertMany(promo_stats);
