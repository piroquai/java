public class Main {
    public static void main(String[] args) throws InterruptedException {
        Stable kingsStable = new Stable(10);
        Runnable steal = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (kingsStable.horses>0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        kingsStable.horses--;
                    } else {
                        System.out.println("G: NO HORSES");
                    }
                    if (kingsStable.horses<0){
                        System.out.println("G : Horses" + kingsStable.horses);
                        System.exit(0);
                    }
                    System.out.println("G: Horses = " + kingsStable.horses);
                }
            }
        };
        Thread gypsy = new Thread(steal);
        gypsy.start();
        Thread gypsy2 = new Thread(steal);
        gypsy2.start();

        Runnable spawn = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (kingsStable.horses<0){
                        System.out.println("P : Horses" + kingsStable.horses);
                        System.exit(0);
                    }
                    kingsStable.horses++;
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("P: Horses = " + kingsStable.horses);
                }

            }
        };
        Thread peasant = new Thread(spawn);
        peasant.start();
    }
}
