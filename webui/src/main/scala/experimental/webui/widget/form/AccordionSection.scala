package experimental.webui.widget.form

import com.vaadin.ui.{VerticalLayout, Accordion, Component}

case class AccordionSection(pages: SubPages) extends Section {
  override protected def render: Component = {
    val ret = new Accordion
    ret addStyleName "borderless"
    ret setSizeFull()

    for (page <- pages.pages) {
      val vbox = new VerticalLayout
      vbox setMargin true

      vbox setCaption page.title
      ret addComponent vbox

      for (section <- page.sections.sections) {
        vbox addComponent section.component
      }
    }

    return ret
  }
}
