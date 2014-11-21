package computacaografica;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jouwee
 */
public class ExtratorCaracteristicas {

    private final Imagem imagem;
    private final List<ObjetoImagem> objetos;
    private int background;
    private int foreground;

    public ExtratorCaracteristicas(Imagem imagem) {
        this.imagem = imagem.clone();
        this.objetos = new ArrayList<>();

        // TODO: Parametrizar
        background = 255;
        foreground = 0;

    }

    public void executa() {
        // Separa os objetos os objetos
        separaObjetos();
        // Extrai dados geométricos basicos (Área e perímetro)
        extraiGeometria();
        // Encontra os vértices dos bojetos selecionados
        encontraVertices();
        // Classifica todos os objetos
        classificaObjetos();
    }

    /**
     * Separa os objetos na imagem
     */
    public void separaObjetos() {
        int nextColor = 1;
        for(int y = 0; y < imagem.getHeight(); y++) {
            for(int x = 0; x < imagem.getWidth(); x++) {
                // Se for cor de frente
                if(imagem.getPixel(x, y) == foreground) {
                    // Cria o objeto
                    ObjetoImagem obj = new ObjetoImagem();
                    obj.setCor(nextColor);
                    objetos.add(obj);
                    // Coloriza os pixeis vizinhos de forma recursiva
                    colorNeighbours(x, y, nextColor);
                    nextColor++;
                }
            }
        }
    }

    /**
     * Coloriza os vizinhos que forem da cor foreground da cor especificada de
     * forma recursiva, até colorir todo o objeto
     *
     * @param x
     * @param y
     * @param color
     */
    public void colorNeighbours(int x, int y, int color) {
        imagem.setPixel(x, y, color);
        if(y > 0 && imagem.getPixel(x, y - 1) == foreground) {
            colorNeighbours(x, y - 1, color);
        }
        if(y < imagem.getHeight() - 1 && imagem.getPixel(x, y + 1) == foreground) {
            colorNeighbours(x, y + 1, color);
        }
        if(x > 0 && imagem.getPixel(x - 1, y) == foreground) {
            colorNeighbours(x - 1, y, color);
        }
        if(x < imagem.getWidth() - 1 && imagem.getPixel(x + 1, y) == foreground) {
            colorNeighbours(x + 1, y, color);
        }
    }

    /**
     * Extrai informações geométricas básicas da imagem
     */
    public void extraiGeometria() {
        for(int y = 0; y < imagem.getHeight(); y++) {
            for(int x = 0; x < imagem.getWidth(); x++) {
                // Se não for cor de fundo
                if(imagem.getPixel(x, y) != background) {
                    ObjetoImagem objeto = getObjetoByCor(imagem.getPixel(x, y));
                    // Incrementa a área
                    objeto.addToArea(new Point(x, y));
                    // Se estiver na borda da imagem
                    if(x == 0 || y == 0 || x == imagem.getWidth() - 1 || y == imagem.getHeight() - 1) {
                        objeto.addToPerimetro(new Point(x, y));
                    } else {
                        if(imagem.getPixel(x, y - 1) == background ||
                           imagem.getPixel(x - 1, y) == background || imagem.getPixel(x + 1, y) == background ||
                           imagem.getPixel(x, y + 1) == background) {
                            objeto.addToPerimetro(new Point(x, y));
                        }
                    }
                }
            }
        }

        for (ObjetoImagem objeto : objetos) {
            // Ordena os vértices
            sortPerimetro(objeto);
        }

    }

    /**
     * Encontra os vértices dos objetos previamente classificados
     */
    public void encontraVertices() {
        // Percorre os objetos
        for (ObjetoImagem objeto : objetos) {
            // Percorre o perímetro dos objetos
            for (Point point : objeto.getPerimetro()) {
                // Obs: " " = fundo, "#" = frente, "*" = qualquer um


                // Cantos de 90º
                if(matches(point, objeto.getCor(), "*****",
                                                   "**   ",
                                                   "* ###",
                                                   "* #**",
                                                   "* #**")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "* #**",
                                                   "* #**",
                                                   "* ###",
                                                   "**   ",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "**# *",
                                                   "**# *",
                                                   "### *",
                                                   "   **",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "   **",
                                                   "### *",
                                                   "**# *",
                                                   "**# *")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }


                if(matches(point, objeto.getCor(), "*****",
                                                   "*****",
                                                   "**###",
                                                   "**#  ",
                                                   "**# *")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "*****",
                                                   "###**",
                                                   "  #**",
                                                   "* #**")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "**# *",
                                                   "**#  ",
                                                   "**###",
                                                   "*****",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "* #**",
                                                   "  #**",
                                                   "###**",
                                                   "*****",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }

                // Pontas de linhas
                if(matches(point, objeto.getCor(), "* # *",
                                                   "* # *",
                                                   "* # *",
                                                   "** **",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "** **",
                                                   "* # *",
                                                   "* # *",
                                                   "* # *")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "   **",
                                                   "### *",
                                                   "   **",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "**  *",
                                                   "* ###",
                                                   "**   ",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }

                // Triângulos
                if(matches(point, objeto.getCor(), "*****",
                                                   "** **",
                                                   "* # *",
                                                   " ### ",
                                                   "#####")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "#####",
                                                   " ### ",
                                                   "* # *",
                                                   "** **",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "# ***",
                                                   "## **",
                                                   "### *",
                                                   "## **",
                                                   "# ***")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*** #",
                                                   "** ##",
                                                   "* ###",
                                                   "** ##",
                                                   "*** #")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }

                if(matches(point, objeto.getCor(), "*****",
                                                   "** **",
                                                   "* # *",
                                                   " ## *",
                                                   "### *")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "** **",
                                                   "* # *",
                                                   "* ## ",
                                                   "* ###")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "* ###",
                                                   "* ## ",
                                                   "* # *",
                                                   "** **",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "### *",
                                                   " ## *",
                                                   "* # *",
                                                   "** **",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "   **",
                                                   "### *",
                                                   "## **",
                                                   "# ***")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "# ***",
                                                   "## **",
                                                   "### *",
                                                   "   **",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*****",
                                                   "**   ",
                                                   "* ###",
                                                   "** ##",
                                                   "*** #")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }
                if(matches(point, objeto.getCor(), "*** #",
                                                   "** ##",
                                                   "* ###",
                                                   "**   ",
                                                   "*****")) {
                    objeto.addVertice(new Vertice(point.x, point.y));
                }



            }
        }
    }

    /**
     *
     * @param color Verifica se o pixel atual bate com a máscara
     *
     * @param p
     * @param masks
     * @return
     */
    public boolean matches(Point p, int color, String... masks) {
        int height = masks.length;
        int width = masks[0].length();
        int[][] mask = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mask[i][j] = masks[i].charAt(j) == ' ' ? background : masks[i].charAt(j) == '#' ? color : -1;
            }
        }
        // Carrega os pixels
        int[][] pixels = new int[height][width];
        int hx = width / 2;
        int hy = height / 2;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = getSafePixel(p.x + x - hx, p.y + y - hy);
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(mask[y][x] > -1 && pixels[y][x] != mask[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSafePixel(int x, int y) {
        if(x < 0 || y < 0 || x >= imagem.getWidth() || y >= imagem.getHeight()) {
            return background;
        }
        return imagem.getPixel(x, y);
    }

    /**
     * Retorna o objeto à partir da cor
     *
     * @param cor
     * @return ObjetoImagem
     */
    public ObjetoImagem getObjetoByCor(int cor) {
        for (ObjetoImagem objetoImagem : objetos) {
            if(objetoImagem.getCor() == cor) {
                return objetoImagem;
            }
        }
        return null;
    }

    /**
     * Ordena o perímetro em sentido horário
     *
     * @param objeto
     */
    private void sortPerimetro(ObjetoImagem objeto) {
        List<Point> sorted = new ArrayList<>();
        List<Point> toSort = new ArrayList<>(objeto.getPerimetro());

        Point current;
        current = toSort.get(0);
        sorted.add(toSort.get(0));
        toSort.remove(0);

        while(!toSort.isEmpty()) {
            if(toSort.size() == 1) {
                sorted.add(toSort.get(0));
                current = toSort.get(0);
                toSort.remove(0);
                break;
            }
            boolean processou = false;
            for (Point aux : toSort) {
                // Se for vizinho
                if(aux.x >= current.x - 1 && aux.x <= current.x + 1 && aux.y >= current.y - 1 && aux.y <= current.y + 1) {
                    sorted.add(aux);
                    toSort.remove(aux);
                    current = aux;
                    processou = true;
                    break;
                }
            }
            if(!processou) {
                sorted.add(toSort.get(0));
                current = toSort.get(0);
                toSort.remove(0);
            }
        }


//        objeto.setPerimetro(sorted);
    }

    /**
     * Classifica os tipos de objetos
     */
    public void classificaObjetos() {
        for (ObjetoImagem objeto : objetos) {
            // Se for um objeto de até 4 pixels e sem vértices
            if(objeto.getArea().size() <= 4 && objeto.getVertices().isEmpty()) {
                objeto.setTipo("Ponto");
                continue;
            }

            // Se for um objeto com 1 linha de espessura (área == perímetro) e tiver 2 ou mais pontos
            if(objeto.getArea().size() == objeto.getPerimetro().size() && objeto.getVertices().size() >= 2) {
                objeto.setTipo("Linha");
                continue;
            }
            // Se tiver exatamente 4 vértices
            if(objeto.getVertices().size() == 4) {
                objeto.setTipo("Quadrilátero");
                // Percorre os vértices na ordem de ligação
                List<Vertice> localVertices = new ArrayList<>(objeto.getVertices());
                // Coloca os primeiros vértice no fim pra facilitar a iteração
                localVertices.add(localVertices.get(0));
                localVertices.add(localVertices.get(1));
                for (int i = 0; i < objeto.getVertices().size() - 2; i++) {
                    // Ref: https://br.answers.yahoo.com/question/index?qid=20090316205502AAVQt2U
                    Vertice a = objeto.getVertices().get(i);
                    Vertice b = objeto.getVertices().get(i + 1);
                    Vertice c = objeto.getVertices().get(i + 2);
                    // Coeficiente angular
                    double caBC = 0;
                    double caBA = 0;
                    if(c.getX() != b.getX()) {
                        caBC = ((double)c.getY() - (double)b.getY()) / ((double)c.getX() - (double)b.getX());
                    }
                    if(a.getX() != b.getX()) {
                        caBA = ((double)a.getY() - (double)b.getY()) / ((double)a.getX() - (double)b.getX());
                    }
                    // Angulos
                    double aBC = Math.toDegrees(Math.atan(caBC));
                    double aBA = Math.toDegrees(Math.atan(caBA));
                    // Angulo
                    double ang = Math.abs(aBC) + Math.abs(aBA);

                }
                continue;
            }
            // Se tiver exatamente 3 vértices
            if(objeto.getVertices().size() == 3) {
                objeto.setTipo("Triângulo");
                continue;
            }
            // Se não tem vértices
            if(objeto.getVertices().isEmpty()) {
                double p = objeto.getPerimetro().size();
                double a = objeto.getArea().size();
                double c = (p * p) / (4 * Math.PI * a);
                objeto.setCircularidade(c);
                if(c > 0.75 && c < 1.1) {
                    objeto.setTipo("Círculo");
                }
            }
        }
    }

    public Imagem getImagem() {
        return imagem;
    }

    public List<ObjetoImagem> getObjetos() {
        return objetos;
    }

}
