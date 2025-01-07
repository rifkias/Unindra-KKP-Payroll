/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 80040
 Source Host           : 127.0.0.1:3306
 Source Schema         : payroll_java2

 Target Server Type    : MySQL
 Target Server Version : 80040
 File Encoding         : 65001

 Date: 07/01/2025 21:31:54
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of absensi
-- ----------------------------
BEGIN;
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
INSERT INTO `division` VALUES (2, 2, 'Infra', 'TIM INFRA BKS');
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
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gaji
-- ----------------------------
BEGIN;
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
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of gaji_detail
-- ----------------------------
BEGIN;
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
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
