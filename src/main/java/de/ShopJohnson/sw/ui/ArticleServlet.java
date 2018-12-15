package de.ShopJohnson.sw.ui;

import de.ShopJohnson.sw.service.ArticleService;
import de.ShopJohsnon.sw.entity.Article;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/ArticleAdministration"})
public class ArticleServlet extends HttpServlet {

    @Inject
    private ArticleService articleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();

        Article article = new Article();

        articleService.addArticleToRange(new Article("Essen", 10000));

        articleService.addArticleToRange(new Article("Drinks", 0.1f));

        out.println("<html>");
        out.println("<head/>");
        out.println("<body>");
        out.println("<h1>Article administration<h1>");

//        out.println("<form method=\"post\">");
//        if ("load".equals(req.getParameter("action"))) {
//            String artNr = req.getParameter("artnr");
//            article = articleService.getArticleById(Long.parseLong(artNr));
//            out.println("Article Nr: ");
//            out.println(artNr + "<br/>");
//        }
//
//        out.println("Name:");
//        out.println("<input type=\"text\" name=\"article_name\" value=\"");
//        if (article.getArtName() != null)
//            out.println(article.getArtName());
//        out.println("\"/><br/>");
//        out.println("Price");
//        out.println("<input type=\"text\" name=\"price\" value=\"");
//
//        out.println(article.getPrice());
//        out.println("\"/><br/>");
//
//        if ("load".equals(req.getParameter("action"))) {
//            String artNr = req.getParameter("artnr");
//            out.println("<input type=\"hidden\" name=\"artnr\" value=\"" + artNr + "\">");
//            out.println("<input type=\"submit\" value=\"Change\" name=\"action\"/><br/>");
//        } else {
//            out.println("<input type=\"submit\" value=\"Add\" name=\"action\"/><br/>");
//        }
//
//        if ("AddArticle".equals(req.getParameter("action"))) {
//            article = new Article(
//                    req.getParameter("article_name"),
//                    Float.parseFloat(req.getParameter("price"))
//            );
//            article = articleService.addArticleToRange(article);
//        } else if ("Change".equals(req.getParameter("action"))) {
//            article = new Article(
//                    req.getParameter("article_name"),
//                    Float.parseFloat(req.getParameter("price"))
//            );
//            article.setArtNr(Long.parseLong(req.getParameter("artnr")));
//            article = articleService.changeArticleData(article);
//        } else if ("delete".equals(req.getParameter("action"))) {
//            article = new Article();
//            article.setArtNr(Long.parseLong(req.getParameter("artnr")));
//            article = articleService.removeArticleFromRange(article);
//        }
//        out.println("</form>");

        List<Article> all = articleService.getAllArticles();
        out.println("<table>");
        for (Article art : all) {
            out.println("<tr>");
            out.println("<td>" + art.getArtNr() + "</td>");
            out.println("<td>" + art.getArtName() + "</td>");
            out.println("<td>" + art.getPrice() + "</td>");
            out.println("<td><form method=\"post\"><input type=\"hidden\" name=\"artnr\" value=\"" + art.getArtNr() + "\"><input type=\"submit\" name=\"action\" value=\"load\">&nbsp;<input type=\"submit\" name=\"action\" value=\"delete\"></form></td>");
            out.println("<tr>");
        }
        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
