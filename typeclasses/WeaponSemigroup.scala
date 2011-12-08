import com.github.kxbmap.scalaadvent2011.day8.Weapon
import scalaz._, Scalaz._

semigroup[Weapon]((w1, w2) => throw new IllegalArgumentException("duplicate key: " + w1 + ", " + w2))