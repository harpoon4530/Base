//import java.util.List;
//import java.util.Set;
//
//public class Pulumi {
//
//
//    /*
//    In the language of your choice, implement logic to check for the following object state:
//- .Status.Conditions has a condition with Type: "Ready" and Status: "True"
//- .Status.Conditions has no condition with Type: "Error" and Status: "True"
//
//Print a message indicating the state of the object.
//
//---------
//
//// Pod structure
//Pod {
//  Metadata {
//    Name: string,
//  },
//  Status {
//    Conditions [
//      Condition {
//        Type: string,   // Type can be “Initialized”, “Scheduled”, “Ready”, "Error"
//        Status: string, // Status can be “True”, “False”, “Unknown”
//        Reason: string,
//      },
//    ],
//  },
//}
//
//     */
//
//    public static class Pod {
//        MetaData metaData;
//        StatusObj status;
//
//    }
//
//    public static class StatusObj {
//        public List<Conditions> conditionsList;
//    }
//
//    public static class MetaData {
//        public String name;
//    }
//
//    public static enum Status1 {
//        Unknown,
//        True,
//        False
//    }
//
//
//    public static class Conditions {
//        public String type;
//        public Status1 status;
//    }
//
//
//
//
//    public static boolean readyAndTrue(Pod pod) {
//
//        boolean found = false;
//
//        // Get List of conditions
//        List<Conditions> conditionsList = pod.status.conditionsList;
//
//        for (Conditions c : conditionsList) {
//            if (c.status.equals("True") && c.type.equals("Ready")) {
//                found = true;
//                break;
//            }
//        }
//        return found;
//    }
//
//    public static boolean noErrorAndTrue(Pod pod) {
//
//        boolean noErrorAndTrue = true;
//
//        // Get List of conditions
//        List<Conditions> conditionsList = pod.status.conditionsList;
//
//        for (Conditions c : conditionsList) {
//            if (c.status.equals("True") && c.type.equals("Error")) {
//                noErrorAndTrue = false;
//                break;
//            }
//        }
//
//        return noErrorAndTrue;
//    }
//
//    public static void main(String[] args) {
//
//        // Init pod
//        Set<Pod> pods = null;
//
//        for (Pod p : pods) {
//            boolean readyAndTrue = readyAndTrue(p);
//            boolean noErrorAndTrue = noErrorAndTrue(p);
//
//            /*
//            readyAndTrue & noErrorAndTrue
//            !readyAndTrue & noErrorAndTrue
//            readyAndTrue & !noErrorAndTrue
//            !readyAndTrue & !noErrorAndTrue
//             */
//
//            boolean ready = readyAndTrue & noErrorAndTrue;
//            System.out.println("Pod state: " + ready + "\t with condition: " + condition);
//        }
//
//
//        System.out.println("Hello world...!");
//    }
//
//}
