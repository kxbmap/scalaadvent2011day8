import com.github.kxbmap.scalaadvent2011.day8._
import scala.tools.nsc.io.Path

(base: GameConfig, path: Path) => new GameConfig with sclz.ConfigReader {

  val weapons = localize(base.weapons, path / "weapons.scala")

}
