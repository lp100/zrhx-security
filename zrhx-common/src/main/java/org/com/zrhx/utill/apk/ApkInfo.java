package org.com.zrhx.utill.apk;
/**
 * @author gs
 * @desc [apk实体信息]
 */
public  class ApkInfo{
        /**
         * apk内部版本号
         */
        private String versionCode = null;
        /**
         * apk外部版本号
         */
        private String versionName = null;
        /**
         * apk的包名
         */
        private String packageName = null;
        /**
         * 应用程序名
         */
        private String applicationLable;

        private String code;
        
        private String error;

        public ApkInfo() {
        }


        /**
         * 返回版本代码。
         * 
         * @return 版本代码。
         */
        public String getVersionCode() {
            return versionCode;
        }

        /**
         * @param versionCode
         *            the versionCode to set
         */
        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        /**
         * 返回版本名称。
         * 
         * @return 版本名称。
         */
        public String getVersionName() {
            return versionName;
        }

        /**
         * @param versionName
         *            the versionName to set
         */
        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
        /**
         * 返回包名。
         * 
         * @return 返回的包名。
         */
        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
        /**
         * 返回程序的名称标签。
         * 
         * @return
         */
        public String getApplicationLable() {
            return applicationLable;
        }

        public void setApplicationLable(String applicationLable) {
            this.applicationLable = applicationLable;
        }


		public String getCode() {
			return code;
		}


		public void setCode(String code) {
			this.code = code;
		}


		public String getError() {
			return error;
		}


		public void setError(String error) {
			this.error = error;
		}


		@Override
		public String toString() {
			return "ApkInfo [versionCode=" + versionCode + ", versionName="
					+ versionName + ", packageName=" + packageName
					+ ", applicationLable=" + applicationLable + ", code="
					+ code + ", error=" + error + "]";
		}
        

    }