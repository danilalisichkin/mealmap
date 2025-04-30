import { StatusHistoryDto } from "../api/user/dto/StatusHistoryDto";
import { UserDto } from "../api/user/dto/UserDto";
import { Role } from "../api/user/enums/Role";
import { StatusEvent } from "../api/user/enums/StatusEvent";

export const mockUser: UserDto = {
  id: "0e3901b5-fb3f-44e4-b691-adac0a735789",
  phoneNumber: "375291234567",
  email: "client@company.com",
  firstName: "Иванов",
  lastName: "Иван",
  organizationId: 3,
  status: {
    isActive: true,
    isTemporaryBlocked: false,
    isBlocked: false,
  },
  roles: [Role.CUSTOMER],
  createdAt: "2023-10-01T12:00:00Z",
};

export const mockUserStatusHistory: StatusHistoryDto[] = [
  {
    id: 3,
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    newStatus: StatusEvent.UNBLOCK,
    reason: "конец временной блокировки",
    changedBy: "ef38cc33-c3df-4b76-aa8c-de6be555f45c",
    eventAt: "2023-10-01T12:00:00Z",
  },
  {
    id: 2,
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    newStatus: StatusEvent.TEMPORARY_BLOCK,
    reason: "недостаточно средств",
    changedBy: "ef38cc33-c3df-4b76-aa8c-de6be555f45c",
    eventAt: "2023-09-01T12:00:00Z",
  },
  {
    id: 1,
    userId: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    newStatus: StatusEvent.ACTIVATE,
    reason: "активация учетной записи",
    changedBy: "0e3901b5-fb3f-44e4-b691-adac0a735789",
    eventAt: "2023-03-01T12:00:00Z",
  },
];
