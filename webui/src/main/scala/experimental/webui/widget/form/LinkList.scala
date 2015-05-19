package experimental.webui.widget.form

import com.vaadin.ui.{Button, Component, Label, VerticalLayout}

case class Link(caption: String)
case class Links(links: Link*)

case class LinkList(
    caption: String = "",
    links: Links = Links()) extends Control(caption = caption) {

  override protected def render: Component = {
    val ret = new VerticalLayout

    if (!this.caption.trim().isEmpty) {
      val titleLabel = new Label(this.caption)
      ret addComponent titleLabel
    }

    for (link <- links.links) {
      val linkButton = new Button(link.caption)
      linkButton addStyleName "link"
      ret addComponent linkButton
    }

    ret
  }
}
