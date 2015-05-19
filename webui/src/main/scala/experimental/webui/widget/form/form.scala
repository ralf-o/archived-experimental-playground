package experimental.webui.widget.form

import com.vaadin.ui._
import experimental.webui.widget.{ToolBar, Widget}

class DataForm(
    title: String = "",
    toolbar: Option[ToolBar] = Option.empty,
    sections: Sections,
    margin: Boolean = true) extends Widget {

  override protected def render: Component = {
    val ret = new VerticalLayout
    ret.setSizeFull()

    val header = new HorizontalLayout
    header.setHeightUndefined()
    header setSpacing true
    ret addComponent header

    val titleLabel = new Label(title)
    titleLabel.addStyleName("h3 large")
    header addComponent titleLabel
    header.setComponentAlignment(titleLabel, Alignment.MIDDLE_LEFT)

    if (this.toolbar.isDefined) {
      val component = toolbar.get.component
      header addComponent component
      header.setComponentAlignment(component, Alignment.MIDDLE_LEFT)
    }

    ret.setMargin(this.margin)

    sections.sections.foreach(section => {
      ret.addComponent(section.component)
      ret.setComponentAlignment(section.component, Alignment.TOP_LEFT)

      if (section.expanded) {
        ret.setExpandRatio(section.component, 1);
      } else {
        ret.setExpandRatio(section.component, 0);
      }
    })

    ret
  }
}

abstract class Control(caption: String) extends Widget


case class Controls(controls: Control*)


case class TextInput(caption: String) extends Control(caption) {
  def render(): Component = {
    val ret = new TextField(caption)
    ret
  }
}


trait Section extends Widget {
  def expanded: Boolean = false
}

final case class Sections(sections: Section*)


final case class SingleControlSection(control: Control) extends Section {
  override protected def render: Component = {
    val ret = control.component
    ret.setSizeFull()
    ret
  }
}

final case class FieldSet(
    title: String = "",
    controls: Controls = Controls()) extends Section {

  override def render(): Component = {
    val ret = new FormLayout
    //ret.setHeightUndefined()




    if (this.title != null && !this.title.trim().isEmpty) {
      ret.setCaption(this.title)
    }


    for (control <- controls.controls) {
      ret.addComponent(control.component)
    }

    return ret
  }
}

final case class FieldSets(sections: FieldSet*)

final case class SubPage(
    title: String,
    sections: Sections = Sections())

final case class SubPages(pages: SubPage*)

final case class TabBox(
    framed: Boolean = false,
    override val expanded: Boolean = true,
    pages: SubPages) extends Section {

  override def render(): Component = {
    val ret = new TabSheet
    ret.setSizeFull()

    if (this.framed) {
      ret addStyleName "framed"
    }

    for (page <- this.pages.pages) {
      val vbox = new VerticalLayout
      vbox.setSizeFull()
      vbox.setCaption(page.title)
      vbox.setMargin(this.framed)

      for (section <- page.sections.sections) {
        vbox addComponent section.component

        if (section.expanded) {
          vbox.setExpandRatio(section.component, 1)
        } else {
          vbox.setExpandRatio(section.component, 0)
        }
      }

      ret addComponent vbox
    }

    return ret
  }
}
