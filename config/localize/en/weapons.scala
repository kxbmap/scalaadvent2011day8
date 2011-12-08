import com.github.kxbmap.scalaadvent2011.day8.Weapon

Map[Int, Weapon => Weapon](
  1 -> (_.copy(name = "knife", attack = 15)),
  2 -> (_.copy(name = "bow")),
  3 -> (_.copy(name = "axe"))
)
