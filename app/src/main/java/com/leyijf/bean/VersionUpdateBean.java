package com.leyijf.bean;

/**
 *版本更新bean
 */
public class VersionUpdateBean {


        /**
         * serverVersion : 2018060210
         * filename : http://www.leyi.com/android.apk
         * android_upgrade : android测试。。
         * hasfile : 0
         * filesize : 0
         * has_upgrade : 0
         * forced_upgrade : 0
         */

        private int serverVersion;
        private String filename;
        private String android_upgrade;
        private int hasfile;
        private int filesize;
        private int has_upgrade;
        private int forced_upgrade;

        public int getServerVersion() {
            return serverVersion;
        }

        public void setServerVersion(int serverVersion) {
            this.serverVersion = serverVersion;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getAndroid_upgrade() {
            return android_upgrade;
        }

        public void setAndroid_upgrade(String android_upgrade) {
            this.android_upgrade = android_upgrade;
        }

        public int getHasfile() {
            return hasfile;
        }

        public void setHasfile(int hasfile) {
            this.hasfile = hasfile;
        }

        public int getFilesize() {
            return filesize;
        }

        public void setFilesize(int filesize) {
            this.filesize = filesize;
        }

        public int getHas_upgrade() {
            return has_upgrade;
        }

        public void setHas_upgrade(int has_upgrade) {
            this.has_upgrade = has_upgrade;
        }

        public int getForced_upgrade() {
            return forced_upgrade;
        }

        public void setForced_upgrade(int forced_upgrade) {
            this.forced_upgrade = forced_upgrade;
        }

}
