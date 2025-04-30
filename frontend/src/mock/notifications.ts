import { NotificationDto } from "../api/notification/dto/NotificationDto";
import { Channel } from "../api/notification/enums/Channel";
import { NotificationStatus } from "../api/notification/enums/NotificationStatus";

export const mockNotifications: NotificationDto[] = [
  {
    id: "1",
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    channel: Channel.EMAIL,
    status: NotificationStatus.SENT,
    subject: "Ваш заказ принят",
    message: "Спасибо за заказ! Мы начали его обработку.",
    sentAt: "2025-04-22T10:00:00Z",
  },
  {
    id: "2",
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    channel: Channel.TELEGRAM,
    status: NotificationStatus.SENT,
    subject: "Заказ готов к выдаче",
    message: "Ваш заказ №123 готов к выдаче. Приятного аппетита!",
    sentAt: "2025-04-22T11:00:00Z",
  },
  {
    id: "3",
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    channel: Channel.SMS,
    status: NotificationStatus.FAILED,
    subject: "Ошибка доставки уведомления",
    message: "Не удалось доставить SMS-уведомление.",
    sentAt: "2025-04-22T11:05:00Z",
  },
  {
    id: "4",
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    channel: Channel.TELEGRAM,
    status: NotificationStatus.PENDING,
    subject: "Скоро доставка",
    message: "Курьер уже в пути, ожидайте заказ в течение 30 минут.",
    sentAt: "2025-04-22T11:10:00Z",
  },
  {
    id: "5",
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    channel: Channel.EMAIL,
    status: NotificationStatus.SENT,
    subject: "Спасибо за покупку!",
    message: "Будем рады видеть вас снова в MealMap!",
    sentAt: "2025-04-22T12:00:00Z",
  },
];
