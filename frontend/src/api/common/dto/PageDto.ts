export interface PageDto<T> {
  currentPage: number;
  pageSize: number;
  totalPages: number;
  totalElements: number;
  items: T[];
}
