# Danh sách nhóm
# Public URL của webservice
# Hướng dẫn cách chạy

# Câu trả lời cho các phần lab

## **Lab 1**

#### - Dữ liệu lớn:
![thêm 10 sinh viên](Ex1.png)

#### - Ràng buộc khoá chính (Primary Key):
Trong bảng `students`, cột `id` được khai báo:

```sql
id INTEGER PRIMARY KEY
```
Mỗi giá trị của khoá chính phải:

- **Không được trùng lặp (UNIQUE)**
- **Không được NULL**
- Mỗi bảng chỉ có **một Primary Key**


Khi thêm một sinh viên có `id = 1` trong khi bảng đã tồn tại `id = 1`, hệ thống sẽ báo lỗi:
```
UNIQUE constraint failed: students.id
```
Database chặn thao tác này để đảm bảo:

+ Tính toàn vẹn dữ liệu (Data Integrity)
Mỗi sinh viên phải có một mã số duy nhất. Nếu cho phép trùng, hệ thống sẽ không thể phân biệt được hai sinh viên.

+ Tránh mâu thuẫn dữ liệu
Nếu tồn tại hai bản ghi cùng `id`, các thao tác như:UPDATE, DELETE, JOIN với bảng khác

sẽ gây ra sai lệch hoặc không xác định chính xác bản ghi cần xử lý.

+ Đảm bảo tính nhất quán (Consistency)
Hệ quản trị cơ sở dữ liệu (DBMS) luôn đảm bảo dữ liệu sau mỗi thao tác phải ở trạng thái hợp 



#### - Toàn vẹn dữ liệu (Constraints)
Giả sử thực hiện câu lệnh:

```sql
INSERT INTO students (id, name, email, age)
VALUES (20, NULL, 'test@example.com', 22);
```
--> Database **Không báo lỗi** vì cột name khi tạo bảng không có ràng buộc *NOT NULL*

Việc không khai báo `NOT NULL` cho những trường quan trọng có thể gây ra nhiều vấn đề khi lập trình Java đọc dữ liệu lên.


- Ảnh hưởng khi đọc dữ liệu bằng Java
```
NullPointerException
```
- Ảnh hưởng ở tầng nghiệp vụ (Business Logic)
```
- Không thể hiển thị tên sinh viên
- Gây lỗi khi validate dữ liệu
- Ảnh hưởng logic tìm kiếm, sắp xếp
- Có thể gây lỗi khi serialize sang JSON
```

- Ảnh hưởng đến chất lượng dữ liệu
```
- Dữ liệu không đầy đủ
- Mất tính chuyên nghiệp của hệ thống
- Khó kiểm soát lỗi về sau
```

#### - Cấu hình hibernate:
Nguyên nhân mỗi lần tắt ứng dụng và chạy lại thì dữ liệu trong DB bị mất
```
spring.jpa.hibernate.ddl-auto=create
 ```
Khi ứng dụng chạy:

- Hibernate **xoá toàn bộ bảng cũ**
- Sau đó **tạo lại bảng mới từ đầu**
- Dữ liệu cũ sẽ bị mất hoàn toàn

→ Hibernate lại tạo schema mới  
→ Database bị reset  
→ Dữ liệu trước đó biến mất




