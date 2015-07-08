package experimental.webui

import com.vaadin.annotations.Theme
import com.vaadin.server.{FontAwesome, Sizeable, VaadinRequest}
import com.vaadin.ui.{HorizontalSplitPanel, Notification, UI}
import experimental.webui.data.PageFetchResult.Success
import experimental.webui.data.{PageableDatasource, PagingPosition}
import experimental.webui.widget.{ActionMenu, Actions, ColumnGroup, ColumnGroups, Columns, DataNavigator, GeneralAction, GroupedActions, ToolBar, _}
import experimental.webui.widget.form._


@Theme("webui")
class WebUI extends UI {
  override def init(request: VaadinRequest) = {
    val ds = new PageableDatasource[String]((pageIdx, pageSize) => {
      new Success[String](Range(0, pageSize).map(n => "X" + String.valueOf(n + pageIdx * pageSize + 1)), PagingPosition.Position(pageIdx = pageIdx, totalItemCount = 10000, pageSize = pageSize))
    })

    class Controller {
      def createNew(): Unit = {

      }

      def delete(items: Seq[String]): Unit = {

      }

      def edit(item: String): Unit = {

      }

      def whatever(item: String): Unit = {

      }

      def whatever2(item: Seq[String]): Unit = {

      }
    }

    val controller = new Controller()

    val dataNav = new DataNavigator[String](
      dataSource = ds,
      actions = GroupedActions(
        Actions(
          GeneralAction(
            caption = "New",
            icon = Option(FontAwesome.FILE_O),
            command = () => Notification.show("Clicked 'New'")),
          SingleSelectAction(
            caption = "Edit",
            icon = Option(FontAwesome.EDIT),
            command = selection =>Notification.show(selection)),
          MultiSelectAction(
            caption = "Delete",
            icon = Option(FontAwesome.TRASH_O),
            command = selection => Notification.show(selection.toString))),
        Actions(
          ActionMenu(
            caption = "Export",
            icon = Option(FontAwesome.DOWNLOAD),
            actions = Actions(
              SingleSelectAction(
                caption = "Export all"),
              ActionMenu(
                caption = "Export selected",
                actions = Actions(
                  GeneralAction(
                    caption = "X"),
                  ActionMenu(
                    caption = "Another sub menu",
                    actions = Actions(
                      SingleSelectAction(
                        caption = "Y"),
                      ActionMenu(
                        caption = "Yet another sub menu",
                        actions = Actions(
                          GeneralAction(
                            caption = "Z"))))))))))),
      columns = ColumnGroups(
        ColumnGroup(
          title = "Meta1",
          columns = Columns(
            Column(
              title = "spalte1",
              render = (s: String) => "A" + s),
            Column(
              title = "spalte2",
              render = (s: String) => "B" + s))),
        ColumnGroup(
          title = "Meta2",
          columns = Columns(
            Column(
              title = "spalte3",
              render = (s: String) => "C" + s),
            Column(
              title = "spalte4",
              render = (s: String) => "D" + s)))))

    val pageGeneralSections = Sections(
      FieldSet(
        controls = Controls(
          TextInput(
            caption = "Product-No."),
          TextInput(
            caption = "Description"))),
      TabBox(
        framed = true,
        pages = SubPages(
          SubPage(
            title = "Base data",
            sections = Sections(
              FieldSet(
                controls = Controls(
                  TextInput(
                    caption = "Product-No."),
                  TextInput(
                    caption = "Description"))))),
          SubPage(
            title = "Variants"))))

    val pageVariantsSections = Sections(
      SingleControlSection(
        control = dataNav))

    val myForm = new DataForm(
      title = "Products",
      toolbar = Option(ToolBar(
        tools = Tools(
          Tools(
            ToolButton(
              caption = "Save"),
            ToolButton(
              caption = "Delete"),
            ToolButton(
              caption = "Cancel")),
          ToolButton(
            caption = "Export",
            menu = ToolMenuItems(
              ToolMenuCommand(
                caption = "Menu item 1"),
              ToolMenu(
                caption = "Menu item 2",
                items = ToolMenuItems(
                  ToolMenuCommand(
                    caption = "Menu item 3")))))))),
    sections = Sections(
      TabBox(
        pages = SubPages(
          SubPage(
            title = "General",
            sections = pageGeneralSections),
          SubPage(
            title = "Variants",
            sections = pageVariantsSections)))))

    val sideBar = new DataForm(
      margin = false,
      sections = Sections(
        AccordionSection(
          pages = SubPages(
            SubPage(
              title = "User Management",
              sections = Sections(
                SingleControlSection(
                  LinkList(
                    caption = "Group 1",
                    links = Links(
                      Link(
                        caption = "Users"),
                      Link(
                        caption = "User groups"),
                      Link(
                        caption = "Permission")))))),
            SubPage(
              title = "Asset Management",
              sections = Sections(
                 SingleControlSection(
                   LinkList(
                     caption = "Group 2",
                     links = Links(
                       Link(
                         caption = "Module A"),
                       Link(
                         caption = "Module B"),
                       Link(
                         caption = "Module C")))))),
            SubPage(
              title = "Catalog data",
              sections = Sections(
                SingleControlSection(
                  LinkList(
                    caption = "Group 3",
                    links = Links(
                      Link(
                        caption = "Products"),
                      Link(
                        caption = "Categories"),
                      Link(
                        caption = "Warehouses"))))))))))

    sideBar.component.setHeight("100%")
    sideBar.component.setWidth("100%")
    sideBar.component.setSizeFull()



    //sideBar.component.addStyleName("")
    //sideBar.component.setHeight("100%")


     val hsplit = new HorizontalSplitPanel
    myForm.component.setSizeFull()
   // htsplit setSizeFull()

    hsplit setFirstComponent  sideBar.component


    hsplit setSecondComponent  myForm.component
    hsplit.setSplitPosition(200, Sizeable.Unit.PIXELS);
    this.setContent(hsplit)
  }
}







