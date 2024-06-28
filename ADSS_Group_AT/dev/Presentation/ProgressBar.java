package Presentation;

public class ProgressBar {
    private int total;
    private int current;
    private int barLength;

    public ProgressBar(int total, int barLength) {
        this.total = total;
        this.barLength = barLength;
        this.current = 0;
    }

    public void update(int progress) {
        current += progress;
        int percentage = (int) ((double) current / total * 100);
        int filledLength = (int) (barLength * current / total);
        StringBuilder bar = new StringBuilder("[");

        for (int i = 0; i < filledLength; i++) {
            bar.append("#");
        }
        for (int i = filledLength; i < barLength; i++) {
            bar.append(" ");
        }

        bar.append("] ").append(percentage).append("%");
        System.out.print("\r" + bar.toString());
    }

    public void finish() {
        update(total - current);
        System.out.println();
    }
}

