USE `framework`;

-- SYSTEM INFORMATION

DROP TABLE IF EXISTS `SystemInformation`;
CREATE TABLE `SystemInformation` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `PropertyName` TEXT NOT NULL,
  `PropertyValue` TEXT NOT NULL,
  `TimestampOfRecord` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `SystemInformationIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains system information';

-- USER --

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserName` TEXT NOT NULL,
  `Password` TEXT NOT NULL,
  `Salt` TEXT NOT NULL,
  `Email` TEXT NOT NULL,
  `Status` CHAR(1) NOT NULL,
  `ActivationToken` TEXT NOT NULL,
  `Language` CHAR(2) NOT NULL,
  `Version` INT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UserIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains information about user';

DROP TABLE IF EXISTS `UserHistory`;
CREATE TABLE `UserHistory` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserName` TEXT NOT NULL,
  `Email` TEXT NOT NULL,
  `Status` CHAR(1) NOT NULL, 
  `Language` CHAR(2) NOT NULL,
  `TimestampOfChange` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UserHistoryIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains historical information about user';

-- USER ROLES --

DROP TABLE IF EXISTS `UserRoles`;
CREATE TABLE `UserRoles` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT, 
  `UserName` TEXT NOT NULL, 
  `Role` TEXT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UserRolesIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains information about user roles';

DROP TABLE IF EXISTS `UserRolesHistory`;
CREATE TABLE `UserRolesHistory` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT, 
  `UserName` TEXT NOT NULL, 
  `Role` TEXT NOT NULL,
  `TimestampOfChange` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `UserRolesHistoryIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains historical information about user roles';

-- ROLE PERMISSIONS --

DROP TABLE IF EXISTS `RolePermissions`;
CREATE TABLE `RolePermissions` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT, 
  `Permission` TEXT NOT NULL, 
  `Role` TEXT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `RolePermissionsIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains information about role permissions';

DROP TABLE IF EXISTS `RolePermissionsHistory`;
CREATE TABLE `RolePermissionsHistory` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT, 
  `Permission` TEXT NOT NULL, 
  `Role` TEXT NOT NULL,
  `TimestampOfChange` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `RolePermissionsHistoryIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains historical information about role permissions';

-- COUNTRY --

DROP TABLE IF EXISTS `Country`;
CREATE TABLE `Country` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT, 
  `Alpha2` CHAR(2) NOT NULL,
  `Alpha3` CHAR(3) NOT NULL,
  `CountryCode` INT NOT NULL,
  `CountryNameEN` TEXT NULL,
  `CountryNameSK` TEXT NULL,
  `CountryNameOriginal` TEXT NULL,
  `Region` TEXT NULL,
  `RegionCode` INT NULL, 
  `SubRegion` TEXT NULL,
  `SubRegionCode` INT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `CountryIdIndex` (`Id` ASC)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_slovak_ci
COMMENT = 'Table contains information about countries';
