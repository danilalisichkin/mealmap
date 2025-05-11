export interface UserContactsDto {
  id: string; // ObjectId as a string
  userId: string; // UUID
  email: string;
  phoneNumber: string;
  tgChatId: number;
}
