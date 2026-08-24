var row = "even";
var other = "odd";
var swap = "";

function w(text)
{
	document.write(text);
}

function writeTD (c)
{
	w("<TD CLASS=\"");
	w(row);
	w(c);
	w("\">");
	return true;
}

function writeTDnowrap (c)
{
	w("<TD CLASS=\"");
	w(row);
	w(c);
	w("\" NOWRAP>");
	return true;
}

function resetRows()
{
	row = "even";
	other = "odd";
	swap = "";
}

function swapRows()
{
	swap = row;
	row = other;
	other = swap;
}

function setNavigation(doc_url, help_url, is_doc)
{
	if(parent == null || parent.frames == null || parent.frames.menu == null || parent.frames.menu.document == null)
		return false;

	if(parent.frames.menu.moveArrow != null)
	{
		if(is_doc != null) parent.frames.menu.moveArrow(doc_url);
		else
		parent.frames.menu.moveArrow(doc_url+location.search);
 	}

	if(parent.frames.menu.document.forms["urlsaver"] != null && parent.frames.menu.document.forms["urlsaver"].helpURL != null)
		parent.frames.menu.document.forms["urlsaver"].helpURL.value = help_url;
        return true;
}  
