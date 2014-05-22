package ru.terra.tboard.constants;

import ru.terra.server.constants.CoreUrlConstants;

/**
 * Date: 27.02.14
 * Time: 15:57
 */
public class URLConstants extends CoreUrlConstants {
    public static class Ui {
        public static final String UI = "ui";
        public static final String UPLOAD = "/upload";
        public static final String MAIN = "main";
        public static final String BOARD = "/board/{board}";
        public static final String THREAD = BOARD + "/t/{thread}";
        public static final String POST = "/post/{post}";
    }

    public static class Resources {
        public static final String RESOURCES = "resources";
    }

    public static class Post {
        public static final String POST = "post";
        public static final String ADD = "do.add";
        public static final String GET_BOARD = "do.get.board.json";
        public static final String GET_THREAD = "do.get.thread.json";
    }
}
