const MONGO_HOST = "localhost:27022";
const DATABASE = "promo_database";
const COLLECTION_NAME = "promo_codes";

const promo_codes = [
  {
    value: 'SPRING 2025',
    limit: 100,
    discountPercentage: 10,
    startDate: new Date('2025-03-01'),
    endDate: new Date('2025-06-01'),
    _class: 'com.mealmap.promoservice.doc.PromoCode'
  },
  {
    value: '2025',
    limit: 9999,
    discountPercentage: 5,
    startDate: new Date('2025-01-01'),
    endDate: new Date('2026-01-01'),
    _class: 'com.mealmap.promoservice.doc.PromoCode'
  },
];

const db = connect(`${MONGO_HOST}/${DATABASE}`);

db[COLLECTION_NAME].insertMany(promo_codes);
