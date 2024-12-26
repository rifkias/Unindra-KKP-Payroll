/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 80040
 Source Host           : 127.0.0.1:3306
 Source Schema         : payroll_java

 Target Server Type    : MySQL
 Target Server Version : 80040
 File Encoding         : 65001

 Date: 27/12/2024 01:56:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for absensi
-- ----------------------------
DROP TABLE IF EXISTS `absensi`;
CREATE TABLE `absensi` (
  `absensi_id` int NOT NULL AUTO_INCREMENT,
  `employe_id` int DEFAULT NULL,
  `absensi_date` date DEFAULT NULL,
  `check_in` datetime DEFAULT NULL,
  `check_out` datetime DEFAULT NULL,
  PRIMARY KEY (`absensi_id`),
  KEY `employe_reference_idx` (`employe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of absensi
-- ----------------------------
BEGIN;
INSERT INTO `absensi` VALUES (3, 1, '2024-04-23', '2024-04-23 16:20:50', '2024-04-23 23:20:53');
INSERT INTO `absensi` VALUES (4, 1, '2024-04-24', '2024-04-24 21:54:56', '2024-04-25 12:02:43');
INSERT INTO `absensi` VALUES (5, 1, '2024-05-02', '2024-05-02 10:15:18', '2024-05-02 16:40:01');
INSERT INTO `absensi` VALUES (6, 1, '2024-05-04', '2024-05-04 05:41:50', '2024-05-04 08:38:26');
INSERT INTO `absensi` VALUES (7, 1, '2024-05-11', '2024-05-11 07:54:14', '2024-05-11 17:49:08');
INSERT INTO `absensi` VALUES (8, 12, '2024-05-11', '2024-05-11 07:49:24', '2024-05-11 19:49:26');
INSERT INTO `absensi` VALUES (9, 12, '2024-05-10', '2024-05-10 05:17:06', '2024-05-10 20:17:08');
INSERT INTO `absensi` VALUES (10, 1, '2024-09-18', '2024-09-18 15:24:49', '2024-11-05 11:16:25');
INSERT INTO `absensi` VALUES (11, 1, '2024-12-03', '2024-12-03 09:57:46', '2024-12-03 09:57:48');
INSERT INTO `absensi` VALUES (12, 1, '2024-12-07', '2024-12-07 22:27:40', '2024-12-07 23:18:04');
INSERT INTO `absensi` VALUES (13, 1, '2024-12-21', '2024-12-21 08:31:42', '2024-12-25 19:14:49');
COMMIT;

-- ----------------------------
-- Table structure for departement
-- ----------------------------
DROP TABLE IF EXISTS `departement`;
CREATE TABLE `departement` (
  `departement_id` int NOT NULL AUTO_INCREMENT,
  `location_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`departement_id`),
  KEY `location_reference_idx` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of departement
-- ----------------------------
BEGIN;
INSERT INTO `departement` VALUES (2, 4, 'Departement IT', 'IT HO');
INSERT INTO `departement` VALUES (3, 6, 'Departement IT', 'IT Bekasi');
INSERT INTO `departement` VALUES (6, 6, 'Departement Keuangan', 'Keuangan Bekasi');
INSERT INTO `departement` VALUES (7, 4, 'Departement HR', 'Dept HR HO');
INSERT INTO `departement` VALUES (8, 6, 'Departement Operational', 'Dept OPS Bekasi');
INSERT INTO `departement` VALUES (9, 11, 'Departement Operational', 'Departement Operational Cibitung');
INSERT INTO `departement` VALUES (10, 12, 'Departement Operational', 'Departement Operational Karawang EDIT');
INSERT INTO `departement` VALUES (11, 4, 'TEST', 'TEST');
COMMIT;

-- ----------------------------
-- Table structure for division
-- ----------------------------
DROP TABLE IF EXISTS `division`;
CREATE TABLE `division` (
  `division_id` int NOT NULL AUTO_INCREMENT,
  `departement_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` text,
  PRIMARY KEY (`division_id`),
  KEY `departement_id_idx` (`departement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of division
-- ----------------------------
BEGIN;
INSERT INTO `division` VALUES (1, 2, 'Infra', 'TIM INFRA HO');
INSERT INTO `division` VALUES (2, 3, 'Infra', 'TIM INFRA BKS');
INSERT INTO `division` VALUES (6, 2, 'Programmer', 'Division Programmer HO');
INSERT INTO `division` VALUES (7, 6, 'Pajak', 'Div Pajak  - Dept Keuangan - Site Bekasi');
INSERT INTO `division` VALUES (8, 7, 'HRD', 'Divisi HRD HO');
INSERT INTO `division` VALUES (9, 7, 'GA', 'Divisi GA HO');
INSERT INTO `division` VALUES (10, 8, 'Operator Forklfit', 'Operator Forklift Bekasi');
INSERT INTO `division` VALUES (11, 8, 'Picker', 'Picker Bekasi');
INSERT INTO `division` VALUES (12, 8, 'Packing', 'Divisi Packing Bekasi');
INSERT INTO `division` VALUES (13, 9, 'Operational Cibitung', 'Operational Cibitung');
INSERT INTO `division` VALUES (14, 10, 'Operational Karawang', 'Operational Karawang\n');
INSERT INTO `division` VALUES (15, 2, 'TEST UPDATE', 'TEST');
COMMIT;

-- ----------------------------
-- Table structure for employe
-- ----------------------------
DROP TABLE IF EXISTS `employe`;
CREATE TABLE `employe` (
  `employe_id` int NOT NULL AUTO_INCREMENT,
  `employe_name` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `nik` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  `departement_id` int DEFAULT NULL,
  `division_id` int DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `is_active` tinyint DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT (now()),
  `created_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employe_id`),
  KEY `location_reference_idx` (`location_id`),
  KEY `departement_reference_idx` (`departement_id`),
  KEY `division_reference_idx` (`division_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of employe
-- ----------------------------
BEGIN;
INSERT INTO `employe` VALUES (1, 'Rifki', '2003-07-03', '202143500723', '202143500723', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 4, 2, 2, 'admin', 5430000.123, 1, '2024-04-05 05:39:45', '');
INSERT INTO `employe` VALUES (10, 'HRD HO', '2000-05-01', '1234567', 'hrho', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 4, 7, 8, 'admin', 4500000, 1, '2024-05-11 07:39:56', 'Rifki');
INSERT INTO `employe` VALUES (11, 'Karyawan 1', '2024-05-01', '1234561', 'karyawan1', '94ee059335e587e501cc4bf90613e0814f00a7b08bc7c648fd865a2af6a22cc2', 11, 9, 13, 'user', 2500000, 1, '2024-05-11 07:40:53', 'Rifki');
INSERT INTO `employe` VALUES (12, 'Karyawan 2', '2024-05-01', '1234562', 'karyawan2', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 6, 8, 11, 'user', 3000000, 1, '2024-05-11 07:41:26', 'Rifki');
INSERT INTO `employe` VALUES (13, 'karyawan 3', '2024-05-01', '1234563', 'karyawan3', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 6, 8, 12, 'user', 3000000, 1, '2024-05-11 07:42:05', 'Rifki');
INSERT INTO `employe` VALUES (14, 'Karyawan 4', '2024-05-08', '1234564', '1234564', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 6, 6, 7, 'user', 9000000, 0, '2024-05-11 19:30:28', 'Rifki');
INSERT INTO `employe` VALUES (15, 'TEST', '2000-04-01', '231321', 'wrasfas', '469727123e308d6cdf438ea4fb1aebcaa7b23e4dee7d699e4f8468440424df3d', 6, 3, 2, 'admin', 2222222, 1, '2024-12-08 19:48:30', 'Rifki');
COMMIT;

-- ----------------------------
-- Table structure for employe_insurance
-- ----------------------------
DROP TABLE IF EXISTS `employe_insurance`;
CREATE TABLE `employe_insurance` (
  `employe_insurance_id` int NOT NULL AUTO_INCREMENT,
  `employe_id` int NOT NULL,
  `insurance_id` int NOT NULL,
  PRIMARY KEY (`employe_insurance_id`),
  KEY `employe_fk_idx` (`employe_id`),
  KEY `insurance_fk_idx` (`insurance_id`),
  CONSTRAINT `employe_fk` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`employe_id`) ON DELETE CASCADE,
  CONSTRAINT `insurance_fk` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`asuransi_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of employe_insurance
-- ----------------------------
BEGIN;
INSERT INTO `employe_insurance` VALUES (2, 12, 1);
INSERT INTO `employe_insurance` VALUES (3, 1, 5);
INSERT INTO `employe_insurance` VALUES (4, 1, 4);
COMMIT;

-- ----------------------------
-- Table structure for gaji
-- ----------------------------
DROP TABLE IF EXISTS `gaji`;
CREATE TABLE `gaji` (
  `gaji_id` int NOT NULL AUTO_INCREMENT,
  `employe_id` int DEFAULT NULL,
  `total_absen` int DEFAULT NULL,
  `total_lembur` int DEFAULT NULL,
  `periode_id` int DEFAULT NULL,
  `total_pendapatan` double DEFAULT NULL,
  `total_pengurangan` double DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`gaji_id`),
  KEY `fk_employe_idx` (`employe_id`),
  KEY `fk_periode_idx` (`periode_id`),
  CONSTRAINT `fk_employe` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`employe_id`),
  CONSTRAINT `fk_periode` FOREIGN KEY (`periode_id`) REFERENCES `periode` (`periode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gaji
-- ----------------------------
BEGIN;
INSERT INTO `gaji` VALUES (93, 1, 2, 0, 1, 6730000, 218600, '2024-12-27 01:53:32', '2024-12-27 01:53:32');
INSERT INTO `gaji` VALUES (94, 10, 0, 0, 1, 4500000, 90000, '2024-12-27 01:53:32', '2024-12-27 01:53:32');
INSERT INTO `gaji` VALUES (95, 11, 0, 0, 1, 2500000, 50000, '2024-12-27 01:53:32', '2024-12-27 01:53:32');
INSERT INTO `gaji` VALUES (96, 12, 0, 0, 1, 3000000, 90000, '2024-12-27 01:53:32', '2024-12-27 01:53:32');
INSERT INTO `gaji` VALUES (97, 13, 0, 0, 1, 3000000, 60000, '2024-12-27 01:53:32', '2024-12-27 01:53:32');
INSERT INTO `gaji` VALUES (98, 15, 0, 0, 1, 2222222, 44444, '2024-12-27 01:53:32', '2024-12-27 01:53:32');
COMMIT;

-- ----------------------------
-- Table structure for gaji_detail
-- ----------------------------
DROP TABLE IF EXISTS `gaji_detail`;
CREATE TABLE `gaji_detail` (
  `gaji_detail_id` int NOT NULL AUTO_INCREMENT,
  `gaji_id` int DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `remarks` text,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`gaji_detail_id`),
  KEY `fk_gaji_detail_1_idx` (`gaji_id`),
  CONSTRAINT `header_gaji` FOREIGN KEY (`gaji_id`) REFERENCES `gaji` (`gaji_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gaji_detail
-- ----------------------------
BEGIN;
INSERT INTO `gaji_detail` VALUES (234, 93, 'Lembur', 'Total Lembur = 0', 0);
INSERT INTO `gaji_detail` VALUES (235, 93, 'Absen', 'Total Absen = 2', 100000);
INSERT INTO `gaji_detail` VALUES (236, 93, 'Pajak', 'Pajak Penghasilan', -108600.00245999999);
INSERT INTO `gaji_detail` VALUES (237, 93, 'Reimbursment', 'No 123-12 : Makan Siang', 200000);
INSERT INTO `gaji_detail` VALUES (238, 93, 'Reimbursment', 'No 123-12 : Hotel', 1000000);
INSERT INTO `gaji_detail` VALUES (239, 93, 'Asuransi', 'Asuransi Kendaraan', -30000);
INSERT INTO `gaji_detail` VALUES (240, 93, 'Asuransi', 'Asuransi Kesehatan', -80000);
INSERT INTO `gaji_detail` VALUES (241, 94, 'Lembur', 'Total Lembur = 0', 0);
INSERT INTO `gaji_detail` VALUES (242, 94, 'Absen', 'Total Absen = 0', 0);
INSERT INTO `gaji_detail` VALUES (243, 94, 'Pajak', 'Pajak Penghasilan', -90000);
INSERT INTO `gaji_detail` VALUES (244, 95, 'Lembur', 'Total Lembur = 0', 0);
INSERT INTO `gaji_detail` VALUES (245, 95, 'Absen', 'Total Absen = 0', 0);
INSERT INTO `gaji_detail` VALUES (246, 95, 'Pajak', 'Pajak Penghasilan', -50000);
INSERT INTO `gaji_detail` VALUES (247, 96, 'Lembur', 'Total Lembur = 0', 0);
INSERT INTO `gaji_detail` VALUES (248, 96, 'Absen', 'Total Absen = 0', 0);
INSERT INTO `gaji_detail` VALUES (249, 96, 'Pajak', 'Pajak Penghasilan', -60000);
INSERT INTO `gaji_detail` VALUES (250, 96, 'Asuransi', 'Asuransi Kesehatan', -30000);
INSERT INTO `gaji_detail` VALUES (251, 97, 'Lembur', 'Total Lembur = 0', 0);
INSERT INTO `gaji_detail` VALUES (252, 97, 'Absen', 'Total Absen = 0', 0);
INSERT INTO `gaji_detail` VALUES (253, 97, 'Pajak', 'Pajak Penghasilan', -60000);
INSERT INTO `gaji_detail` VALUES (254, 98, 'Lembur', 'Total Lembur = 0', 0);
INSERT INTO `gaji_detail` VALUES (255, 98, 'Absen', 'Total Absen = 0', 0);
INSERT INTO `gaji_detail` VALUES (256, 98, 'Pajak', 'Pajak Penghasilan', -44444.44);
COMMIT;

-- ----------------------------
-- Table structure for insurance
-- ----------------------------
DROP TABLE IF EXISTS `insurance`;
CREATE TABLE `insurance` (
  `asuransi_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `asuransi_class` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `premi` double DEFAULT NULL,
  PRIMARY KEY (`asuransi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of insurance
-- ----------------------------
BEGIN;
INSERT INTO `insurance` VALUES (1, 'Asuransi Kesehatan', 'Kelas 3', 30000);
INSERT INTO `insurance` VALUES (3, 'Asuransi Kesehatan', 'Kelas 2', 50000);
INSERT INTO `insurance` VALUES (4, 'Asuransi Kesehatan', 'Kelas 1', 80000);
INSERT INTO `insurance` VALUES (5, 'Asuransi Kendaraan', 'Mobil', 30000);
COMMIT;

-- ----------------------------
-- Table structure for lembur
-- ----------------------------
DROP TABLE IF EXISTS `lembur`;
CREATE TABLE `lembur` (
  `lembur_id` int NOT NULL AUTO_INCREMENT,
  `absensi_id` int DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `request_from` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT (now()),
  `created_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`lembur_id`) USING BTREE,
  KEY `absensi_reference_idx` (`absensi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of lembur
-- ----------------------------
BEGIN;
INSERT INTO `lembur` VALUES (1, 3, '2024-04-23 19:30:00', '2024-04-23 22:00:00', 1, '2024-05-02 10:06:56', 'Rifki');
INSERT INTO `lembur` VALUES (2, 4, '2024-04-25 00:00:00', '2024-04-25 04:30:00', 1, '2024-05-02 10:15:14', 'Rifki');
INSERT INTO `lembur` VALUES (5, 6, '2024-05-04 06:00:00', '2024-05-04 07:30:00', 1, '2024-05-04 08:39:26', 'Rifki');
INSERT INTO `lembur` VALUES (7, 5, '2024-05-02 11:00:00', '2024-05-02 16:00:00', 1, '2024-05-11 07:54:48', 'Rifki');
INSERT INTO `lembur` VALUES (8, 8, '2024-05-11 08:00:00', '2024-05-11 19:00:00', 12, '2024-05-11 19:52:24', 'Karyawan 2');
INSERT INTO `lembur` VALUES (9, 9, '2024-05-10 06:00:00', '2024-05-10 20:00:00', 12, '2024-05-11 20:38:55', 'Karyawan 2');
INSERT INTO `lembur` VALUES (10, 7, '2024-05-11 14:00:00', '2024-05-11 17:00:00', 1, '2024-05-11 20:55:17', 'Rifki');
COMMIT;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `address_1` text,
  `address_2` text,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `sub_district` varchar(255) DEFAULT NULL,
  `zip_code` varchar(5) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of location
-- ----------------------------
BEGIN;
INSERT INTO `location` VALUES (4, 'TEST', 'asfassa', 'Jakarta', 'Jakarta Utara', 'Jakarta Utara', 'Jakarta Utara', '2101', 'Head Office');
INSERT INTO `location` VALUES (6, 'Bekasi	', '', 'Jawa Barat', 'Bekasi', 'Bekasi Utara', 'Teluk Pucung', '17121', 'Site Bekasi');
INSERT INTO `location` VALUES (11, 'Cibitung', 'Cibitung', 'Jawa Barat', 'Cibitung', 'Cibitung', 'Cibitung', '12345', 'Site Cibitung ');
INSERT INTO `location` VALUES (12, 'Karawang	', 'Karawang', 'Jawa Barat', 'Karawang', 'Karawang', 'Karawang', '12345', 'Site Karawang');
COMMIT;

-- ----------------------------
-- Table structure for pajak
-- ----------------------------
DROP TABLE IF EXISTS `pajak`;
CREATE TABLE `pajak` (
  `pajak_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `percentage` double DEFAULT NULL,
  PRIMARY KEY (`pajak_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pajak
-- ----------------------------
BEGIN;
INSERT INTO `pajak` VALUES (1, 'Pajak Penghasilan', 'Potongan Pajak Penghasilan', 0.02);
COMMIT;

-- ----------------------------
-- Table structure for periode
-- ----------------------------
DROP TABLE IF EXISTS `periode`;
CREATE TABLE `periode` (
  `periode_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`periode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of periode
-- ----------------------------
BEGIN;
INSERT INTO `periode` VALUES (1, 'Week 1 December', '2024-12-01', '2024-12-08', '2024-12-08 13:53:18', '2024-12-08 14:36:53');
INSERT INTO `periode` VALUES (3, 'Week 2 December', '2024-12-08', '2024-12-14', '2024-12-25 20:45:15', '2024-12-25 20:45:15');
COMMIT;

-- ----------------------------
-- Table structure for reimbursment
-- ----------------------------
DROP TABLE IF EXISTS `reimbursment`;
CREATE TABLE `reimbursment` (
  `reimbursment_id` int NOT NULL AUTO_INCREMENT,
  `reimbursment_no` varchar(255) DEFAULT NULL,
  `employe_id` int DEFAULT NULL,
  `request_from` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT (now()),
  `created_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reimbursment_id`),
  KEY `employe_reference_idx` (`employe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of reimbursment
-- ----------------------------
BEGIN;
INSERT INTO `reimbursment` VALUES (5, 'TEST MASUK', 1, 1, '2024-05-09 22:23:07', '202143500723');
INSERT INTO `reimbursment` VALUES (6, 'TEST123', 1, 1, '2024-05-04 21:03:08', '202143500723');
INSERT INTO `reimbursment` VALUES (7, 'Reimburse 240511', 1, 1, '2024-05-11 07:56:24', '202143500723');
INSERT INTO `reimbursment` VALUES (8, 'TEST ADD', 1, 1, '2024-05-11 20:58:42', '202143500723');
INSERT INTO `reimbursment` VALUES (9, 'TEST KARYAWAN', 12, 1, '2024-05-11 22:22:14', '202143500723');
INSERT INTO `reimbursment` VALUES (10, 'TEST', 12, 12, '2024-05-11 22:24:22', 'karyawan2');
INSERT INTO `reimbursment` VALUES (11, '123-12', 1, 1, '2024-12-02 00:36:43', '202143500723');
COMMIT;

-- ----------------------------
-- Table structure for reimbursment_detail
-- ----------------------------
DROP TABLE IF EXISTS `reimbursment_detail`;
CREATE TABLE `reimbursment_detail` (
  `reimbursment_detail_id` int NOT NULL AUTO_INCREMENT,
  `reimbursment_id` int DEFAULT NULL,
  `description` text,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`reimbursment_detail_id`),
  KEY `reimbursment_reference_idx` (`reimbursment_id`),
  CONSTRAINT `reimbursment_reference` FOREIGN KEY (`reimbursment_id`) REFERENCES `reimbursment` (`reimbursment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of reimbursment_detail
-- ----------------------------
BEGIN;
INSERT INTO `reimbursment_detail` VALUES (1, 5, 'MAKAN SIANG', 20000);
INSERT INTO `reimbursment_detail` VALUES (2, 5, 'MAKAN MALEM', 50000);
INSERT INTO `reimbursment_detail` VALUES (3, 6, 'TEST 1', 100000);
INSERT INTO `reimbursment_detail` VALUES (4, 6, 'TEST 2', 2000000);
INSERT INTO `reimbursment_detail` VALUES (5, 6, 'TEST 3', 500000);
INSERT INTO `reimbursment_detail` VALUES (6, 7, 'Meeting Customer', 200000);
INSERT INTO `reimbursment_detail` VALUES (7, 7, 'Makan Siang', 100000);
INSERT INTO `reimbursment_detail` VALUES (8, 7, 'Visit Site', 150000);
INSERT INTO `reimbursment_detail` VALUES (9, 8, 'UANG MAKAN', 50000);
INSERT INTO `reimbursment_detail` VALUES (10, 8, 'TRASPORT', 100000);
INSERT INTO `reimbursment_detail` VALUES (11, 9, 'MAKAN SIANG', 200000);
INSERT INTO `reimbursment_detail` VALUES (12, 9, 'MAKAN MALAM', 300000);
INSERT INTO `reimbursment_detail` VALUES (13, 10, 'TEST 123', 100000);
INSERT INTO `reimbursment_detail` VALUES (14, 11, 'Makan Siang', 200000);
INSERT INTO `reimbursment_detail` VALUES (15, 11, 'Hotel', 1000000);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
