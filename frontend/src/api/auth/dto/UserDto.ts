import { Role } from "../enums/Role";
import { UserStatus } from "./UserStatus";

export interface UserDto {
  id: string; // UUID
  phoneNumber: string;
  email: string;
  firstName: string;
  lastName: string;
  organizationId: number;
  status: UserStatus;
  role: Role;
  createdAt: string; // ISO date string
}
