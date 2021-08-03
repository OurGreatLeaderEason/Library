import java.util.ArrayList;
public class main{
    public static void main(String args[]){
        ArrayList<Book> books=new ArrayList<Book>();
        books.add(new Book("The State and Revolution", "The State and Revolution (1917) is a book by Vladimir Lenin describing the role of the state in society, the necessity of proletarian\n revolution, and the theoretic inadequacies of social democracy in achieving revolution to establish the dictatorship of the proletariat.", true));
        books.add(new Book("On Protracted War", "On Protracted War is a work comprising a series of speeches by Mao Zedong given from 1938 May 26 to June 3 at the Yenan Association for the\n Study of the War of Resistance Against Japan. In it, he calls for a protracted people's war, as a means for small revolutionary groups to fight the power of the state.", false));
        books.add(new Book("Das Kapital", "Das Kapital is a foundational theoretical text in materialist philosophy, economics and politics by Karl Marx. Marx aimed to reveal the economic\n patterns underpinning the capitalist mode of production in contrast to classical political economists such as Adam Smith, Jean-Baptiste Say, David Ricardo and John Stuart Mill.", true));
        books.add(new Book("The Motorcycle Diaries", "The Motorcycle Diaries is a memoir of the Marxist revolutionary Ernesto \"Che\" Guevara. It traces his early travels, as a 23-year-old\n medical student, with his friend Alberto Granado, a 29-year-old biochemist. Leaving Buenos Aires, Argentina, in January 1952 on the back of a sputtering single cylinder 1939 Norton 500cc dubbed La Poderosa, they desired to explore the South America they only knew from books.", true));
        books.add(new Book("The Communist Manifesto", "The Communist Manifesto summarises Marx and Engels' theories concerning the nature of society and politics. It also briefly features their\n ideas for how the capitalist society of the time would eventually be replaced by socialism.", true));
        UI ui=new UI(books, new ArrayList<Book>(), new ArrayList<Account>());
        ui.main();
    }
}