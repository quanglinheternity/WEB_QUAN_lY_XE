-- Xóa dữ liệu cũ (theo thứ tự ngược lại để tránh ràng buộc khóa ngoại)


-- Insert dữ liệu cho bảng nguoi_dung (đã bỏ vai_tro_id)
INSERT INTO nguoi_dung (tai_khoan, mat_khau, email, so_dien_thoai, ho_ten, cmnd_cccd, ngay_sinh, dia_chi, trang_thai, created_at, updated_at) VALUES
('admin', '$2a$10$ABC123', 'admin@vanchuyen.com', '0901111111', 'Nguyễn Văn Admin', '001100110011', '1980-01-01', 'Hà Nội', 1, NOW(), NOW()),
('quanly1', '$2a$10$ABC124', 'quanly1@vanchuyen.com', '0902222222', 'Trần Quản Lý', '001100110012', '1985-05-15', 'Hà Nội', 1, NOW(), NOW()),
('ketoan1', '$2a$10$ABC125', 'ketoan1@vanchuyen.com', '0903333333', 'Lê Thị Kế Toán', '001100110013', '1990-08-20', 'Hà Nội', 1, NOW(), NOW()),
('dieuphoi1', '$2a$10$ABC126', 'dieuphoi1@vanchuyen.com', '0904444444', 'Phạm Điều Phối', '001100110014', '1992-03-10', 'Hà Nội', 1, NOW(), NOW()),
('taixe1', '$2a$10$ABC127', 'taixe1@vanchuyen.com', '0905555555', 'Hoàng Văn Tài', '001100110015', '1988-12-25', 'Hà Nội', 1, NOW(), NOW()),
('taixe2', '$2a$10$ABC128', 'taixe2@vanchuyen.com', '0906666666', 'Nguyễn Văn Lái', '001100110016', '1987-07-15', 'Hải Phòng', 1, NOW(), NOW()),
('taixe3', '$2a$10$ABC129', 'taixe3@vanchuyen.com', '0907777777', 'Trần Văn Lượng', '001100110017', '1991-11-30', 'Quảng Ninh', 1, NOW(), NOW()),
('taixe4', '$2a$10$ABC129', 'taixe4@vanchuyen.com', '0908888888', 'Nguyễn Tiến Huy', '001100110018', '1991-11-30', 'Quảng Ninh', 1, NOW(), NOW());

-- Insert dữ liệu cho bảng tai_xe (giả sử tai_xe có id riêng, không liên quan đến nguoi_dung)
INSERT INTO tai_xe (id, ma_tai_xe, so_gplx, loai_gplx, ngay_cap_gplx, ngay_het_han_gplx, kinh_nghiem_nam, trang_thai_lam_viec, muc_luong_co_ban, created_at, updated_at)
VALUES
(5, 'TX001', 'GPLX001', 'Hạng C', '2018-05-15', '2028-05-15', 8, 1, 12000000, NOW(), NOW()),
(6, 'TX002', 'GPLX002', 'Hạng C', '2019-03-20', '2029-03-20', 6, 1, 11000000, NOW(), NOW()),
(7, 'TX003', 'GPLX003', 'Hạng D', '2020-08-10', '2030-08-10', 4, 1, 13000000, NOW(), NOW()),
(8, 'TX004', 'GPLX004', 'Hạng D', '2020-08-10', '2030-08-10', 4, 1, 13000000, NOW(), NOW());


-- Insert dữ liệu cho bảng loai_xe
INSERT INTO loai_xe (ma_loai_xe, ten_loai_xe, tai_trong_toi_da, mo_ta, trang_thai) VALUES
('LX001', 'Xe tải 1 tấn', 1000.00, 'Xe tải nhỏ chở hàng nhẹ', 1),
('LX002', 'Xe tải 5 tấn', 5000.00, 'Xe tải trung chở hàng cỡ vừa', 1),
('LX003', 'Xe tải 10 tấn', 10000.00, 'Xe tải lớn chở hàng nặng', 1),
('LX004', 'Xe container', 20000.00, 'Xe container chở hàng hóa lớn', 1);

-- Insert dữ liệu cho bảng xe (đã sửa reference đến tai_xe)
INSERT INTO xe (bien_so_xe, loai_xe_id, ten_xe, nam_san_xuat, mau_sac, so_khung, so_may, ngay_dang_kiem, ngay_bao_hiem, tai_xe_chinh_id, trang_thai_hoat_dong, created_at, updated_at) VALUES
('29A-12345', 1, 'Xe tải nhỏ 001', 2020, 'Trắng', 'SK001', 'SM001', '2024-06-01', '2024-12-01', 5, 1, NOW(), NOW()),
('29B-67890', 2, 'Xe tải trung 001', 2019, 'Xanh', 'SK002', 'SM002', '2024-07-01', '2024-12-01', 6, 1, NOW(), NOW()),
('29C-54321', 3, 'Xe tải lớn 001', 2021, 'Đỏ', 'SK003', 'SM003', '2024-08-01', '2024-12-01', 7, 1, NOW(), NOW()),
('29D-98765', 4, 'Container 001', 2018, 'Vàng', 'SK004', 'SM004', '2024-09-01', '2024-12-01', 8, 1, NOW(), NOW());

-- Insert dữ liệu cho bảng lich_su_bao_duong
INSERT INTO lich_su_bao_duong (xe_id, ngay_bao_duong, loai_bao_duong, km_hien_tai, chi_phi, mo_ta_cong_viec, don_vi_thuc_hien, file_chung_tu, created_at) VALUES
(1, '2024-01-15', 'Bảo dưỡng định kỳ', 50000.00, 2500000.00, 'Thay nhớt, lọc gió, kiểm tra hệ thống phanh', 'Trung tâm bảo dưỡng A', 'baoduong_001.pdf', NOW()),
(1, '2024-06-20', 'Bảo dưỡng định kỳ', 75000.00, 3000000.00, 'Thay nhớt, lọc nhiên liệu, kiểm tra động cơ', 'Trung tâm bảo dưỡng A', 'baoduong_002.pdf', NOW()),
(2, '2024-02-10', 'Sửa chữa', 60000.00, 5000000.00, 'Thay thế phanh trước, cân chỉnh động cơ', 'Trung tâm sửa chữa B', 'suachua_001.pdf', NOW());

-- Insert dữ liệu cho bảng tinh_trang_xe
INSERT INTO tinh_trang_xe (xe_id, trang_thai, mo_ta, muc_do_uu_tien, created_at) VALUES
(1, 1, 'Xe hoạt động tốt, đang trong hành trình', 3, NOW()),
(2, 2, 'Xe cần bảo dưỡng nhỏ', 2, NOW()),
(3, 1, 'Xe sẵn sàng nhận chuyến', 3, NOW()),
(4, 3, 'Xe đang sửa chữa, tạm ngừng hoạt động', 1, NOW());

-- Insert dữ liệu cho bảng tuyen_duong
INSERT INTO tuyen_duong (ma_tuyen, ten_tuyen, diem_dau, diem_cuoi, quang_duong_km, thoi_gian_du_kien, mo_ta, chi_phi_du_kien, trang_thai) VALUES
('TD001', 'Hà Nội - Hải Phòng', 'Hà Nội', 'Hải Phòng', 105.50, '02:30:00', 'Tuyến đường cao tốc Hà Nội - Hải Phòng', 2500000.00, 1),
('TD002', 'Hà Nội - Quảng Ninh', 'Hà Nội', 'Hạ Long', 155.00, '03:30:00', 'Tuyến đường quốc lộ 5 và 18', 3500000.00, 1),
('TD003', 'Hải Phòng - Quảng Ninh', 'Hải Phòng', 'Hạ Long', 75.00, '01:45:00', 'Tuyến đường ven biển', 1800000.00, 1),
('TD004', 'Hà Nội - Hà Nam', 'Hà Nội', 'Phủ Lý', 60.00, '01:30:00', 'Tuyến đường quốc lộ 1A', 1500000.00, 1);

-- Insert dữ liệu cho bảng lich_trinh (đã sửa các reference)
INSERT INTO lich_trinh (ma_chuyen, tuyen_duong_id, xe_id, tai_xe_chinh_id, ngay_khoi_hanh, ngay_du_kien_den, ngay_thuc_te_den, trang_thai, trang_thai_duyet, hang_hoa_mo_ta, trong_luong_hang, created_at, updated_at) VALUES
('CHUYEN001', 1, 1, 5, '2024-12-01 08:00:00', '2024-12-01 10:30:00', '2024-12-01 10:15:00', 2, 1, 'Hàng điện tử, đồ gia dụng', 800.00, NOW(), NOW()),
('CHUYEN002', 2, 2, 6, '2024-12-02 07:00:00', '2024-12-02 10:30:00', NULL, 1, 1, 'Vật liệu xây dựng', 4500.00, NOW(), NOW()),
('CHUYEN003', 3, 3, 7, '2024-12-03 09:00:00', '2024-12-03 10:45:00', NULL, 0, 1, 'Thực phẩm đông lạnh', 8500.00, NOW(), NOW()),
('CHUYEN004', 1, 1, 8, '2024-12-04 14:00:00', '2024-12-04 16:30:00', NULL, 0, 0, 'Hàng may mặc', 750.00, NOW(), NOW());

-- Insert dữ liệu cho bảng loai_chi_phi
INSERT INTO loai_chi_phi (ma_loai_chi_phi, ten_loai_chi_phi, mo_ta, nhom_chi_phi, trang_thai) VALUES
('CP001', 'Chi phí xăng dầu', 'Chi phí nhiên liệu cho xe', 'NHIEN_LIEU', 1),
('CP002', 'Chi phí cầu đường', 'Phí cầu, đường, bến bãi', 'PHI_DUONG_BO', 1),
('CP003', 'Chi phí ăn uống', 'Tiền ăn cho tài xế', 'SINH_HOAT', 1),
('CP004', 'Chi phí bảo dưỡng', 'Sửa chữa, bảo dưỡng xe', 'BAO_DUONG', 1),
('CP005', 'Chi phí phát sinh', 'Các chi phí phát sinh khác', 'PHAT_SINH', 1);

-- Insert dữ liệu cho bảng yeu_cau_chi_phi (đã sửa các reference)
INSERT INTO yeu_cau_chi_phi (ma_yeu_cau, lich_trinh_id, loai_chi_phi_id, so_tien, ngay_chi_phi, dia_diem_chi_phi, mo_ta, trang_thai, created_at, updated_at) VALUES
('YCCP001', 1, 1, 1500000.00, '2024-12-01', 'Trạm xăng Hà Nội', 'Đổ đầy bình xăng trước chuyến đi', 2, NOW(), NOW()),
('YCCP002', 1, 2, 500000.00, '2024-12-01', 'Trạm thu phí BOT', 'Phí cầu đường tuyến Hà Nội - Hải Phòng', 2, NOW(), NOW()),
('YCCP003', 1, 3, 200000.00, '2024-12-01', 'Nhà hàng Hải Phòng', 'Tiền ăn trưa cho tài xế', 1, NOW(), NOW()),
('YCCP004', 2, 1, 2000000.00, '2024-12-02', 'Trạm xăng Hà Nội', 'Đổ xăng cho chuyến Hà Nội - Quảng Ninh', 1, NOW(), NOW());

-- Insert dữ liệu cho bảng bao_cao_luong
INSERT INTO bao_cao_luong (thang_nam, tai_xe_id, so_chuyen_hoan_thanh, tong_quang_duong, luong_co_ban, thuong_chuyen, phu_cap, khau_tru, tong_luong, trang_thai_thanh_toan, created_at, updated_at) VALUES
('2024-11-01', 5, 12, 4500.50, 12000000.00, 3600000.00, 1500000.00, 500000.00, 16600000.00, 1, NOW(), NOW()),
('2024-11-01', 6, 10, 3800.00, 11000000.00, 3000000.00, 1200000.00, 300000.00, 14900000.00, 1, NOW(), NOW()),
('2024-11-01', 7, 8, 3200.75, 13000000.00, 2400000.00, 1000000.00, 0.00, 16400000.00, 1, NOW(), NOW()),
('2024-12-01', 8, 2, 850.00, 12000000.00, 600000.00, 300000.00, 0.00, 12900000.00, 0, NOW(), NOW());