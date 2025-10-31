/**
 * Represents a customer comment for a SalesItem, including the author,
 * the text of the comment, the rating given (1-5), and a vote balance
 * to track helpfulness.
 */
public class Comment {
    private String author;
    private String text;
    private int rating;
    private int voteBalance; // upvotes - downvotes

    /**
     * Creates a new comment.
     * @param author The name of the person who wrote the comment.
     * @param text The content of the comment.
     * @param rating The rating given (1-5).
     */
    public Comment(String author, String text, int rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.voteBalance = 0; // Starts with a neutral balance
    }

    /**
     * Returns the author of the comment.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Increases the helpfulness count (upvote).
     */
    public void upvote() {
        voteBalance++;
    }

    /**
     * Decreases the helpfulness count (downvote).
     */
    public void downvote() {
        voteBalance--;
    }

    /**
     * Returns the helpfulness balance (upvotes - downvotes).
     * This is used by SalesItem.findMostHelpfulComment().
     */
    public int getVoteCount() {
        return voteBalance;
    }

    /**
     * Returns a detailed string representation of the comment.
     */
    public String getFullDetails() {
        String details = "Author: " + author + "\n";
        details += "Rating: " + rating + " / 5\n";
        details += "Helpfulness Balance: " + voteBalance + "\n";
        details += "Comment: " + text;
        return details;
    }
}