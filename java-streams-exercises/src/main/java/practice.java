public class practice {
    public static void main(String[] args) {
        String name = "Hamilton";
        String newName = reverse(name);
        System.out.println("Original Name: " + name);
        System.out.println("Reversed: " + newName);
    }

    static String reverse (String name) {
        if (name == null) return null;
        StringBuilder reverseName = new StringBuilder(name);
        return reverseName.reverse().toString();

//        for(int i = name.length() - 1; i >= 0; i--) {
//            reverseName.append(name.charAt(i));
//        }
//
//        return reverseName.toString();
    }
}
