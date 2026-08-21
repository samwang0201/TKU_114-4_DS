public class GenericResultDemo {

    static class Result<T> {
        private boolean success;
        private String message;
        private T data;

        public Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }
    }

    public static void main(String[] args) {

        Result<String> result1 =
                new Result<String>(true, "取得姓名成功", "小明");

        System.out.println("成功：" + result1.isSuccess());
        System.out.println("訊息：" + result1.getMessage());

        if (result1.getData() != null) {
            String name = result1.getData();
            System.out.println("資料：" + name);
        }

        System.out.println("--------------------");

        Result<Integer> result2 =
                new Result<Integer>(true, "取得分數成功", 90);

        System.out.println("成功：" + result2.isSuccess());
        System.out.println("訊息：" + result2.getMessage());

        if (result2.getData() != null) {
            int score = result2.getData();
            System.out.println("資料：" + score);
        }

        System.out.println("--------------------");

        Result<String> result3 =
                new Result<String>(false, "查無資料", null);

        System.out.println("成功：" + result3.isSuccess());
        System.out.println("訊息：" + result3.getMessage());

        if (result3.getData() == null) {
            System.out.println("資料：null");
        }
    }
}