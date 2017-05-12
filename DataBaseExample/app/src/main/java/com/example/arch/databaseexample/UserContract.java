package com.example.arch.databaseexample;

import android.provider.BaseColumns;

/**
 * Created by arch on 3/18/17.
 */

public class UserContract {

    public static final class UserEntry implements BaseColumns {

        public static final String TABLE_NAME = "users";

        public static final String _ID = BaseColumns._ID;

        public static final String NAME = "name";

        public static final String CITY = "city";

        public static final String AGE = "age";

        public static final String GENDER = "gender";

        public static final int MALE = 1;

        public static final int FEMALE = 0;


    }

}
