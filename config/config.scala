import com.github.kxbmap.scalaadvent2011.day8._
import scala.tools.nsc.io.Path

(path: Path) => new GameConfig with sclz.ConfigReader {

  val weapons = readMap[Int, Weapon](path / "weapons")

}
