import { Channel } from "../enums/Channel";

export interface NotificationCreationDto {
  channel: Channel; // Must not be null
  subject: string; // Max length: 4000
  message: string; // Max length: 4000
}
