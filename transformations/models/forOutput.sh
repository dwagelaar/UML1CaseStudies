#!/bin/sh
#
# Transforms Exceptions back to the standard form used by Poseidon
#

INPUT=$1
if [ ! -f "$INPUT" ]; then
  echo "Error: file \"$INPUT\" not found"
  exit 1
fi

EXCEPTIONS=`grep "<UML:Exception " "$INPUT" | sed "s/^.*xmi\.id = '//" | sed "s/'.*$//g"`
for EXC in $EXCEPTIONS; do
  MERGE="$MERGE OP <UML:Exception xmi.idref = '$EXC'/>"
done

OPERATIONS=`grep -A 1 "<UML:Signal.context>" "$INPUT" | grep "<UML:Operation " | sed "s/^.*xmi\.idref = '//" | sed "s/'.*$//g"`
for OP in $OPERATIONS; do
  MERGE=`echo $MERGE | sed "s/OP/<UML:Operation xmi.idref = '$OP'\/>/"`
done

LENGTH=`grep -c . "$INPUT"`
echo "File length = $LENGTH" >&2

PREVLINE=0
LINES=`grep -n "</\?UML:Signal.context>" "$INPUT" | sed "s/:.*$//"`
for LINE in $LINES; do
  if [ "$FIRSTLINE" = "" ]; then
    FIRSTLINE=$LINE
  else # we have a pair of lines
    echo "Cut: $FIRSTLINE, $LINE" >&2
    tail -n $(($LENGTH - $PREVLINE)) "$INPUT" | head -n $(($FIRSTLINE - $PREVLINE - 1))
    PREVLINE=$LINE
    FIRSTLINE=""
  fi
done
tail -n $(($LENGTH - $PREVLINE)) "$INPUT" | head -n $(($LENGTH - $PREVLINE - 2))

echo "    <UML:A_context_raisedSignal>"
echo $MERGE | sed "s/ </\n      </g" | sed "s/^</      </"
echo "    </UML:A_context_raisedSignal>"

echo "  </XMI.content>"
echo "</XMI>"
